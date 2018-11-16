package com.lud.delivery.cvrptw.route.domain.composite;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;

public class CompositeCalculatedRoute implements CalculatedRoute {


    private CalculatedRoute routeA;
    private CalculatedRoute routeB;
    private List<Location> arc = new LinkedList<>();

    public CompositeCalculatedRoute(CalculatedRoute routeA, CalculatedRoute routeB) {
        this.routeA = routeA;
        this.routeB = routeB;

        this.arc.addAll(routeA.getArc());
        this.arc.add(routeB.getDestiny());
    }

    @Override
    public LocalDateTime getPickupTime() {
        return routeA.getPickupTime();
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return DateTimeUtils.addMilliseconds(routeA.getDeliveryTime(), routeB.getTravelTime());
    }

    @Override
    public Location getOrigin() {
        return routeA.getOrigin();
    }

    @Override
    public Location getDestiny() {
        return routeB.getDestiny();
    }

    @Override
    public Double getTravelTime() {
        return routeA.getTravelTime() + routeB.getTravelTime();
    }

    @Override
    public void setTravelTime(Double calculate) {
        //Do nothing
    }

    @Override
    public boolean isSynthetic() {
        return true;
    }

    @Override
    public Location getCurrentDepot() {
        if(routeB.getCurrentDepot() != null)
            return routeB.getCurrentDepot();

        return routeA.getCurrentDepot();
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
