package fi.sangre.assignment.service.impl;

import fi.sangre.assignment.model.Country;
import fi.sangre.assignment.model.Population;
import fi.sangre.assignment.service.ICountryService;
import fi.sangre.assignment.service.IPopulationService;
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
 * Created by rsubramanian on 8/17/2017.
 */
@Service
public class PopulationService implements IPopulationService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ICountryService countryService;
    private final RestTemplate restTemplate;

    @Autowired
    public PopulationService(RestTemplate restTemplate, ICountryService countryService) {
        this.restTemplate = restTemplate;
        this.countryService = countryService;
    }

    private final static String POPULATION_URL = "http://api.population.io:80/1.0/population/{country}/{date}/";

    @Override
    public Population getTodaysPopulationCountryWise() {
        logger.debug("Inside getAverageLifeExpectancyPerCountry()");
        Country countryList = countryService.getCountries();
        Population population = new Population();
        Map<String, String> countryPopulationToday = new HashMap<String, String>();
        int countryListSize = countryList.getCountries().size();
        String currentDate = Util.getTodaysDate();
        for (int i = 0; i < 3; i++) {
            String randomCountry = Util.getRandomObjectFromList(countryList.getCountries(), countryListSize);
            logger.debug("Country #{}:{}.", i, randomCountry);
            countryPopulationToday.put(randomCountry, String.valueOf(getTodaysPopualtionByCountry(randomCountry,
                    currentDate)));
        }
        population.setCountryPopulationMap(countryPopulationToday);
        logger.debug("Leaving getAverageLifeExpectancyPerCountry()");
        return population;
    }

    private long getTodaysPopualtionByCountry(String country, String currentDate) {
        try {
            Population population = restTemplate.getForObject(POPULATION_URL, Population.class, country,
                    currentDate);
            long totalPopulation = 0;
            if (population != null && population.getTotal_population() != null) {
                totalPopulation = population.getTotal_population().getPopulation();
            }
            logger.debug("Total Population for Country{}={}.", country, totalPopulation);
            return totalPopulation;
        } catch (HttpServerErrorException e) {
            logger.error("Total population could not be retrieved from population.io for country:{}.", country,e);
            return 0;
        }
    }
}


