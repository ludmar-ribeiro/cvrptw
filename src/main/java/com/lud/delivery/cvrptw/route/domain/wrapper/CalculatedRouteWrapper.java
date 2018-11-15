package com.lud.delivery.cvrptw.route.domain.wrapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;

public class CalculatedRouteWrapper implements CalculatedRoute {

    private Route route;

    private Double travelTime;

    public CalculatedRouteWrapper(Route route) {
        this.route = route;
    }

    @Override
    public LocalDateTime getPickupTime() {
        return route.getPickupTime();
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return DateTimeUtils.addMilliseconds(route.getPickupTime(), travelTime);
    }

    @Override
    public Double getTravelTime() {
        return travelTime;
    }

    @Override
    public Location getOrigin() {
        return route.getOrigin();
    }

    @Override
    public Location getDestiny() {
        return route.getDestiny();
    }

    @Override
    public void setTravelTime(Double travelTime) {
        this.travelTime = travelTime;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Location> getArc() {
        return (List<Location>) (List<?>) Arrays.asList(
                    new Location[] {
                            getOrigin(),
                            getDestiny()
                    }
               ); 
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

        Route otherRoute = (Route) obj;

        return this.equalsRoute(otherRoute);
    }
}
