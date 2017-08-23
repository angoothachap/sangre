package fi.sangre.assignment.service.impl;

import fi.sangre.assignment.model.Country;
import fi.sangre.assignment.service.ICountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rsubramanian on 8/16/2017.
 */
@Service
public class CountryService implements ICountryService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final static String COUNTRIES_URL = "http://api.population.io:80/1.0/countries";
    private final RestTemplate restTemplate;

    @Autowired
    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Country getCountries() {
        return restTemplate.getForObject(COUNTRIES_URL, Country.class);
    }
}
