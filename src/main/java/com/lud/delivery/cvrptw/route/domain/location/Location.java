package com.lud.delivery.cvrptw.route.domain.location;

/**
 * Location
 *
 * @author Ludmar Ribeiro
 *
 */
public interface Location {

    /**
     * Latitude
     *
     * @return {@link Double}
     */
    Double getLat();

    /**
     * Longitude
     *
     * @return {@link Double}
     */
    Double getLon();

    /**
     * returns true when this location is a depot
     *
     * @return boolean
     */
    boolean isDepot();
}
