package com.lud.delivery.cvrptw.route.domain.route.wrapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.SyntheticRoute;

/**
 * Sythetic Route Wrpper for two locations
 *
 * @author Ludmar Ribeiro
 *
 */
public class SyntheticRouteWrapper implements SyntheticRoute {

    private OrderedRoute orderedRoute;
    private Location origin;
    private Location destiny;
    private LocalDateTime delivery;
    private LocalDateTime pickup;

    /**
     * Creates a {@link SyntheticRouteWrapper} for two locations
     * 
     * Used to create a route back to a depot
     *
     * @param origin
     * @param destiny
     */
    public SyntheticRouteWrapper(Location origin, Location destiny) {
        this.origin = origin;
        this.destiny = destiny;
        this.pickup = LocalDateTime.MIN;
        this.delivery = LocalDateTime.MAX;
    }

    /**
     * Creates a {@link SyntheticRouteWrapper} for a origin and a {@link OrderedRoute}
     *
     * Used to create a route between two ordered routes
     *
     * @param origin
     * @param orderedRoute
     */
    public SyntheticRouteWrapper(Location origin, OrderedRoute orderedRoute) {
        this.orderedRoute = orderedRoute;
        this.origin = origin;
        this.destiny = orderedRoute.getDestiny();
        this.pickup = orderedRoute.getPickupTime();
        this.delivery = orderedRoute.getDeliveryTime();
    }

    @Override
    public LocalDateTime getPickupTime() {
        return pickup;
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return delivery;
    }

    @Override
    public Location getOrigin() {
        return origin;
    }

    @Override
    public Location getDestiny() {
        return destiny;
    }

    @Override
    public List<OrderedRoute> getOrderedRoutes() {
        if(orderedRoute != null)
            return Collections.singletonList(orderedRoute);

        return Collections.emptyList();
    }

    @Override
    public int hashCode() {
        return routeHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Route))
            return false;

        Route other = (Route) obj;

        return this.equalsRoute(other);
    }
}
