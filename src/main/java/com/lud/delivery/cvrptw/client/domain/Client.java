package com.lud.delivery.cvrptw.client.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

@Entity
public class Client implements Identifiable<Integer>{

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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
