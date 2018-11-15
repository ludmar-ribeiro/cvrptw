package com.lud.delivery.cvrptw.restaurant.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.lud.delivery.cvrptw.common.domain.Identifiable;
import com.lud.delivery.cvrptw.route.domain.Location;

@Entity
public class Restaurant implements Identifiable<Integer>, Location {

    @Id
    @NotNull
    private Integer id;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public boolean isDepot() {
        return true;
    }
}
