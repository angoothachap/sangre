package fi.sangre.assignment.model;

import java.util.Map;

/**
 * Created by rsubramanian on 8/16/2017.
 */
public class LifeExpectancy {
    private Map<String, String> countryLifeExpectancyMap;
    private String dob;
    private String country;
    private float total_life_expectancy;
    private String sex;

    public Map<String, String> getCountryLifeExpectancyMap() {
        return countryLifeExpectancyMap;
    }

    public void setCountryLifeExpectancyMap(Map<String, String> countryLifeExpectancyMap) {
        this.countryLifeExpectancyMap = countryLifeExpectancyMap;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getTotal_life_expectancy() {
        return total_life_expectancy;
    }

    public void setTotal_life_expectancy(float total_life_expectancy) {
        this.total_life_expectancy = total_life_expectancy;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LifeExpectancy{");
        sb.append("countryLifeExpectancyMap=").append(countryLifeExpectancyMap);
        sb.append('}');
        return sb.toString();
    }
}
