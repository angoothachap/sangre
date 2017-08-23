package fi.sangre.assignment.controller;

import fi.sangre.assignment.model.Population;
import fi.sangre.assignment.service.IPopulationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Created by rsubramanian on 8/16/2017.
 */
@RestController
@RequestMapping("/population")

public class PopulationController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IPopulationService populationService;

    @ApiOperation(value = "/v1", notes = "Get Population for any 3 random countries populated in countryPopulationMap as " +
            "registered on population.io ")
    @RequestMapping(value = "/v1", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Population> getPopulation() {
        try {
            logger.info("Received request to fetch life expectancy for date 1952-01-01.");
            return new ResponseEntity(populationService.getTodaysPopulationCountryWise(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception occurred executing the population workflow", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
