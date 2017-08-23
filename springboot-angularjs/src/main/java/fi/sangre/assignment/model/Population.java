package fi.sangre.assignment.model;

import java.util.Map;

/**
 * Created by rsubramanian on 8/16/2017.
 */
public class Population {
    private Map<String, String> countryPopulationMap;
    private TotalPopulation total_population;

    public void setCountryPopulationMap(Map<String, String> countryPopulationMap) {
        this.countryPopulationMap = countryPopulationMap;
    }

    public Map<String, String> getCountryPopulationMap() {
        return countryPopulationMap;
    }

    public TotalPopulation getTotal_population() {
        return total_population;
    }

    public void setTotal_population(TotalPopulation total_population) {
        this.total_population = total_population;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Population{");
        sb.append("countryPopulationMap=").append(countryPopulationMap);
        sb.append('}');
        return sb.toString();
    }
}
