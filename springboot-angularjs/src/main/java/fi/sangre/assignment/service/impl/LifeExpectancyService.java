package fi.sangre.assignment.service.impl;

import fi.sangre.assignment.model.Country;
import fi.sangre.assignment.model.LifeExpectancy;
import fi.sangre.assignment.service.ICountryService;
import fi.sangre.assignment.service.ILifeExpectancyService;
import fi.sangre.assignment.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rsubramanian on 8/18/2017.
 */
@Service
public class LifeExpectancyService implements ILifeExpectancyService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ICountryService countryService;
    private final RestTemplate restTemplate;
    private final static String DATE_OF_BIRTH = "1952-01-01";
    private static final String FEMALE = "female";
    private static String MALE = "male";

    @Autowired
    public LifeExpectancyService(ICountryService countryService, RestTemplate restTemplate) {
        this.countryService = countryService;
        this.restTemplate = restTemplate;
    }

    private final static String LIFE_EXPECTANCY_URL = "http://api.population" +
            ".io:80/1.0/life-expectancy/total/{sex}/{country}/{dob}/";

    @Override
    public LifeExpectancy getAverageLifeExpectancyPerCountry() {
        logger.debug("Inside getAverageLifeExpectancyPerCountry()");
        Country countryList = countryService.getCountries();
        int countryListSize = countryList.getCountries().size();
        Map<String, String> countryLifeExpectancyMap = new HashMap<String, String>();
        LifeExpectancy lifeExpectancy = new LifeExpectancy();
        for (int i = 0; i < 3; i++) {
            String randomCountry = Util.getRandomObjectFromList(countryList.getCountries(), countryListSize);
            logger.debug("Country #{}:{}.", i, randomCountry);
            countryLifeExpectancyMap.put(randomCountry, String.valueOf(calculateAverageLifeExpectancyPerCountry
                    (randomCountry, DATE_OF_BIRTH)));
        }
        lifeExpectancy.setCountryLifeExpectancyMap(countryLifeExpectancyMap);
        logger.debug("Leaving getAverageLifeExpectancyPerCountry()");
        return lifeExpectancy;
    }

    /* I didn't quite understand the meaning of "average life expectancy for persons" from the assignment; Looking at
       the API I guessed that I should get life expectancy for make and female separately and derive average */
    private float calculateAverageLifeExpectancyPerCountry(String country, String currentDate) {
        try {
            float maleLifeExpectancy = 0;
            float femaleLifeExpectancy = 0;
            LifeExpectancy lifeExpectancyMale = restTemplate.getForObject(LIFE_EXPECTANCY_URL,
                    LifeExpectancy.class, FEMALE, country, currentDate);
            if (lifeExpectancyMale != null) maleLifeExpectancy = lifeExpectancyMale.getTotal_life_expectancy();
            LifeExpectancy lifeExpectancyFemale = restTemplate.getForObject(LIFE_EXPECTANCY_URL, LifeExpectancy.class,
                    MALE, country, currentDate);
            if (lifeExpectancyFemale != null) femaleLifeExpectancy = lifeExpectancyFemale.getTotal_life_expectancy();
            float averageLiFeExpectancy = maleLifeExpectancy + femaleLifeExpectancy / 2;
            logger.debug("Average Life Expectancy for Country{}={}.", country, averageLiFeExpectancy);
            return averageLiFeExpectancy;
        } catch (HttpServerErrorException e) {
            logger.error("Life expectancy could not be retrieved from population.io for country:{}.", country, e);
            return 0;
        }
    }
}
