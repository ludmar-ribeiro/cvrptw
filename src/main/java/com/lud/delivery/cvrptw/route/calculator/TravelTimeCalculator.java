package com.lud.delivery.cvrptw.route.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.location.Location;

@Component
public class TravelTimeCalculator {

    @Value("${route.distance.unit}")
    private Double distanceUnit;

    @Value("${route.distance.time-unit}")
    private Double timeUnit;

    public Double calculate(Location origin, Location destiny) {
        return calculateDistance(origin, destiny) / distanceUnit * timeUnit;
    }

    protected Double calculateDistance(Location origin, Location destiny) {

        Double powLat = Math.pow(destiny.getLat() - origin.getLat(), 2);
        Double powLon = Math.pow(destiny.getLon() - origin.getLon(), 2);

        return Math.sqrt(powLat+powLon);
    }
}
