package fi.sangre.assignment.service;

import fi.sangre.assignment.model.LifeExpectancy;

/**
 * Created by rsubramanian on 8/18/2017.
 */
public interface ILifeExpectancyService {
    LifeExpectancy getAverageLifeExpectancyPerCountry();
}
