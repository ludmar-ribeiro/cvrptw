package com.lud.delivery.cvrptw.route.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Travel time calculator
 * 
 * @author Ludmar Ribeiro
 *
 */
@Component
public class TravelTimeCalculator {

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
     * Calculates the travel time between two locations
     * 
     * @param origin
     * @param destiny
     * @return {@link Double} travel time in milliseconds
     */
    public Double calculate(Location origin, Location destiny) {
        return calculateDistance(origin, destiny) / distanceUnit * timeUnit;
    }

    /**
     * Calculates the distance between two points 
     *
     * NOTE: This logic considers lat as X and lon as Y on a 
     *       cartersian plane rather than real latitude and longitude
     * 
     * @param origin
     * @param destiny
     * @return {@link Double}
     */
    protected Double calculateDistance(Location origin, Location destiny) {

        Double powLat = Math.pow(destiny.getLat() - origin.getLat(), 2);
        Double powLon = Math.pow(destiny.getLon() - origin.getLon(), 2);

        return Math.sqrt(powLat+powLon);
    }
}
