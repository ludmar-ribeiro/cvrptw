package com.lud.delivery.cvrptw.route.domain.wrapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.SyntheticRoute;

public class SyntheticRouteWrapper implements SyntheticRoute {

    private OrderedRoute orderedRoute;
    private Location origin;
    private Location destiny;
    private LocalDateTime delivery;
    private LocalDateTime pickup;

    public SyntheticRouteWrapper(Location origin, Location destiny) {
        this.origin = origin;
        this.destiny = destiny;
        this.pickup = LocalDateTime.MIN;
        this.delivery = LocalDateTime.MAX;
    }

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
