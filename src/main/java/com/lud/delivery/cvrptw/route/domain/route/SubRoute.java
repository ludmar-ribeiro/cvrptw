package com.lud.delivery.cvrptw.route.domain.route;

import java.util.List;

import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * SubRoute
 *
 * Route for each trip from a depot to its targets
 *
 * @author Ludmar Ribeiro
 *
 */
public interface SubRoute {

    /**
     * Depot
     *
     * @return {@link Location}
     */
    Location getDepot();

    /**
     * Return a {@link List} of {@link OrderedRoute} delivered on this {@link SubRoute}
     *
     * @return {@link List} of {@link OrderedRoute}
     */
    List<OrderedRoute> getOrderedRoutes();

    /**
     * Adds a {@link OrderedRoute} to this {@link SubRoute}
     *
     * @param route
     */
    void addOrderedRoute(OrderedRoute route);

}
