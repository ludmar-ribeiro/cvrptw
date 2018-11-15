package com.lud.delivery.cvrptw.route.component.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.SimpleLocation;

@Component
@ConfigurationProperties("test-data")
public class TravelTimeCalculateTestData {

    private List<SimpleLocation> origins;
    private List<SimpleLocation> destinies;
    private List<Double> distances;
    private List<Double> travelTimes;

    private List<SimpleLocation> randomOrigins;
    private List<SimpleLocation> randomDestinies;

    @Value("${route.distance.unit}")
    private Double distanceUnit;

    @Value("${route.distance.time-unit}")
    private Double timeUnit;

    public List<SimpleLocation> getRandomOrigins() {
        return randomOrigins;
    }


    public void setRandomOrigins(List<SimpleLocation> randomOrigins) {
        this.randomOrigins = randomOrigins;
    }


    public List<SimpleLocation> getRandomDestinies() {
        return randomDestinies;
    }


    public void setRandomDestinies(List<SimpleLocation> randomDestinies) {
        this.randomDestinies = randomDestinies;
    }


    public Double getRandomDistance(int index) {
        return Math.sqrt(
                    Math.pow(randomDestinies.get(index).getLat() - randomOrigins.get(index).getLat(), 2.0)
                  + Math.pow(randomDestinies.get(index).getLon() - randomOrigins.get(index).getLon(), 2.0)
                );
    }

    public Double getRandomTravelTime(int index) {
        return getRandomDistance(index) / distanceUnit * timeUnit;
    }

    public List<SimpleLocation> getOrigins() {
        return origins;
    }


    public List<SimpleLocation> getDestinies() {
        return destinies;
    }


    public List<Double> getDistances() {
        return distances;
    }


    public void setOrigins(List<SimpleLocation> origins) {
        this.origins = origins;
    }


    public void setDestinies(List<SimpleLocation> destinies) {
        this.destinies = destinies;
    }


    public void setDistances(List<Double> distances) {
        this.distances = distances;
    }


    public List<Double> getTravelTimes() {
        return travelTimes;
    }


    public void setTravelTimes(List<Double> travelTimes) {
        this.travelTimes = travelTimes;
    }
}
