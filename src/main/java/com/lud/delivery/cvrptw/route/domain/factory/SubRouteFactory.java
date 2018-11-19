package com.lud.delivery.cvrptw.route.domain.factory;

import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.SubRoute;

/**
 * SubRoute factory
 *
 * @author Ludmar Ribeiro
 *
 */
public interface SubRouteFactory {

    /**
     * Creates a {@link SubRoute} for a depot
     *
     * @param depot
     * @return {@link SubRoute}
     */
    SubRoute of(Location depot);

}
