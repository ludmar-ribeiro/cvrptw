package com.lud.delivery.cvrptw.client.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lud.delivery.cvrptw.common.domain.Identifiable;
import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Client POJO, Domain object for Client.
 *
 * @author Ludmar Ribeiro
 *
 */
@Entity
public class Client implements Identifiable<Integer>, Location{

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

    @JsonIgnore
    @Override
    public boolean isDepot() {
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getClass().getName().hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Client other = (Client) obj;
        if (id == null) {

            if (other.id != null)
                return false;

        } else if (!id.equals(other.id))
            return false;

        return true;
    }
}
