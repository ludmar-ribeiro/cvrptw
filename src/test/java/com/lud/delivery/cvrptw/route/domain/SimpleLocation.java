package com.lud.delivery.cvrptw.route.domain;

import com.lud.delivery.cvrptw.route.domain.location.Location;

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
