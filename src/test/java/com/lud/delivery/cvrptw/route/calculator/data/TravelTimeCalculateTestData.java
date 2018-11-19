package com.lud.delivery.cvrptw.route.calculator.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.SimpleLocation;

/**
 * Object that holds the test data for the tests about
 * travel time calculation
 *
 * @author Ludmar Ribeiro
 */
@Component
@ConfigurationProperties("test-data")
public class TravelTimeCalculateTestData {

    private List<SimpleLocation> origins;
    private List<SimpleLocation> destinies;
    private List<Double> distances;
    private List<Double> travelTimes;

    private List<SimpleLocation> randomOrigins;
    private List<SimpleLocation> randomDestinies;

    /**
     * Unit of distance used as base for the calculation
     */
    @Value("${route.distance.unit}")
    private Double distanceUnit;

    /**
     * Time unit used as base for the calculation
     */
    @Value("${route.distance.time-unit}")
    private Double timeUnit;

    /**
     * Returns random locations to be used on the uncontrolled tests
     *
     * @return {@link List} of {@link SimpleLocation}
     */
    public List<SimpleLocation> getRandomOrigins() {
        return randomOrigins;
    }

    /**
     * Sets random locations to be used on the uncontrolled tests
     *
     * @param {@link List} of {@link SimpleLocation}
     */
    public void setRandomOrigins(List<SimpleLocation> randomOrigins) {
        this.randomOrigins = randomOrigins;
    }


    /**
     * Returns random locations to be used on the uncontrolled tests
     *
     * @return {@link List} of {@link SimpleLocation}
     */
    public List<SimpleLocation> getRandomDestinies() {
        return randomDestinies;
    }

    /**
     * Sets random locations to be used on the uncontrolled tests
     *
     * @param {@link List} of {@link SimpleLocation}
     */
    public void setRandomDestinies(List<SimpleLocation> randomDestinies) {
        this.randomDestinies = randomDestinies;
    }


    /**
     * Calculate the distance to compare with the implementation of the travel distance
     * calculation logic.
     *
     * This method is used to compare the uncontrolled tests results 
     *
     * @param index
     * @return {@link Double} distance between the random locations index
     */
    public Double getRandomDistance(int index) {
        return Math.sqrt(
                    Math.pow(randomDestinies.get(index).getLat() - randomOrigins.get(index).getLat(), 2.0)
                  + Math.pow(randomDestinies.get(index).getLon() - randomOrigins.get(index).getLon(), 2.0)
                );
    }

    /**
     * Calculate the travel time to compare with the implementation
     * of the travel time calculation logic.
     *
     * This method is used to compare the uncontrolled tests results 
     *
     * @param index
     * @return {@link Double} travel time between the random locations index
     */
    public Double getRandomTravelTime(int index) {
        return getRandomDistance(index) / distanceUnit * timeUnit;
    }

    /**
     * Returns locations to be used during the tests
     *
     * @return {@link List} of {@link SimpleLocation}
     */
    public List<SimpleLocation> getOrigins() {
        return origins;
    }

    /**
     * Returns locations to be used during the tests
     *
     * @return {@link List} of {@link SimpleLocation}
     */
    public List<SimpleLocation> getDestinies() {
        return destinies;
    }

    /**
     * Returns distance values to be used during the tests
     *
     * @return {@link List} of {@link Double}
     */
    public List<Double> getDistances() {
        return distances;
    }

    /**
     * Sets locations to be used during the tests
     *
     * @param {@link List} of {@link SimpleLocation}
     */
    public void setOrigins(List<SimpleLocation> origins) {
        this.origins = origins;
    }


    /**
     * Sets locations to be used during the tests
     *
     * @param {@link List} of {@link SimpleLocation}
     */
    public void setDestinies(List<SimpleLocation> destinies) {
        this.destinies = destinies;
    }

    /**
     * Sets distance values to be used during the tests
     *
     * @param {@link List} of {@link Double}
     */
    public void setDistances(List<Double> distances) {
        this.distances = distances;
    }

    /**
     * Returns travel time values to be used during the tests
     *
     * @return {@link List} of {@link Double}
     */
    public List<Double> getTravelTimes() {
        return travelTimes;
    }

    /**
     * Sets travel time values to be used during the tests
     *
     * @param {@link List} of {@link Double}
     */
    public void setTravelTimes(List<Double> travelTimes) {
        this.travelTimes = travelTimes;
    }
}
