package com.lud.delivery.cvrptw.route.domain.wrapper;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.SyntheticRoute;

public class CalculatedRouteWrapper implements CalculatedRoute {

    private Route route;

    private Double travelTime;

    private List<Location> arc = new LinkedList<>();

    public CalculatedRouteWrapper(Route route) {
        this.route = route;
        this.arc.add(route.getOrigin());
        this.arc.add(route.getDestiny());
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

    @Override
    public boolean isSynthetic() {
        return route instanceof SyntheticRoute;
    }

    @Override
    public Location getCurrentDepot() {
        if(getDestiny().isDepot())
            return getDestiny();

        if(getOrigin().isDepot())
            return getOrigin();

        return null;
    }

    @Override
    public List<Location> getArc() {
        return arc; 
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
