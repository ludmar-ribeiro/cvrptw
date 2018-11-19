package com.lud.delivery.cvrptw.route.domain;

import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * A POJO that implements the Location to be used as data test
 * by the Route Calculation related tests. 
 *
 * @author Ludmar Ribeiro
 *
 */
public class SimpleLocation implements Location {
    private Double lat;
    private Double lon;

    @Override
    public Double getLat() {
        return lat;
    }

    @Override
    public Double getLon() {
        return lon;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public boolean isDepot() {
        return false;
    }
}
