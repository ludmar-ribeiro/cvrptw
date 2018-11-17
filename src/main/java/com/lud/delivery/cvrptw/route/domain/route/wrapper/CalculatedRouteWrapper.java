package com.lud.delivery.cvrptw.route.domain.route.wrapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.SyntheticRoute;

public class CalculatedRouteWrapper implements CalculatedRoute {

    private Route route;

    private Double travelTime;

    private Location currentDepot;

    private List<Location> arc = new LinkedList<>();

    private List<OrderedRoute> orderedRoutes;

    private OrderedRoute lastDelivered;

    private Double lateDeliveryTime = Double.valueOf(0); 

    public CalculatedRouteWrapper(Route route) {
        this.route = route;

        evaluateAttributes(route);
    }

    private void evaluateAttributes(Route route) {
        arc.add(route.getOrigin());
        arc.add(route.getDestiny());

        evaluateCurrentDepot();
        evaluateOrderedRoutes();
        evaluateLastDelivered();
    }

    private void evaluateCurrentDepot() {
        if(getDestiny().isDepot()) {
            currentDepot = getDestiny();
            return;
        }

        if(getOrigin().isDepot())
            currentDepot = getOrigin();
    }

    private void evaluateOrderedRoutes() {
        if(route.isSynthetic()) {
            orderedRoutes = ((SyntheticRoute) route).getOrderedRoutes();
            return;
        }

        orderedRoutes = Collections.singletonList((OrderedRoute) route);
    }

    private void evaluateLastDelivered() {
        if(!orderedRoutes.isEmpty())
            lastDelivered = orderedRoutes.get(0);
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
    public Location getCurrentDepot() {
        return currentDepot;
    }

    @Override
    public List<OrderedRoute> getOrderedRoutes() {
        return orderedRoutes;
    }

    @Override
    public List<Location> getArc() {
        return arc; 
    }

    @Override
    public OrderedRoute getLastDelivered() {
        return lastDelivered;
    }

    @Override
    public Double getLateDeliveryTime() {
        return lateDeliveryTime;
    }

    @Override
    public void setLateDeliveryTime(Double lateDeliveryTime) {
        this.lateDeliveryTime = lateDeliveryTime;
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
