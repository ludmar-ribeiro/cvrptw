package com.lud.delivery.cvrptw.route.domain.composite;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.Route;

public class CompositeCalculatedRoute implements CalculatedRoute {


    private CalculatedRoute routeA;
    private CalculatedRoute routeB;

    private Double travelTimeA;
    private Double travelTimeB;

    private Location origin;
    private Location destiny;

    private LocalDateTime pickup;
    private LocalDateTime lastDelivery;

    private List<Location> arc = new LinkedList<>();

    private List<OrderedRoute> orderedRoutes = new LinkedList<>();

    private Location currentDepot;

    public CompositeCalculatedRoute(CalculatedRoute routeA, CalculatedRoute routeB) {
        this.routeA = routeA;
        this.routeB = routeB;


        evaluateAttributes();
    }

    private void evaluateAttributes() {

        origin = routeA.getOrigin();
        destiny = routeB.getDestiny();

        travelTimeA = routeA.getTravelTime();
        travelTimeB = routeB.getTravelTime();

        pickup = routeA.getPickupTime();
        lastDelivery = routeA.getDeliveryTime();

        evaluateCurrentDepot();

        arc.addAll(routeA.getArc());
        arc.add(routeB.getDestiny());

        orderedRoutes.addAll(routeA.getOrderedRoutes());
        orderedRoutes.addAll(routeB.getOrderedRoutes());
    }

    private void evaluateCurrentDepot() {

        if(routeB.getCurrentDepot() != null) {
            currentDepot = routeB.getCurrentDepot();
            return;
        }

        currentDepot =  routeA.getCurrentDepot();
    }

    @Override
    public LocalDateTime getPickupTime() {
        return pickup;
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return DateTimeUtils.addMilliseconds(lastDelivery, travelTimeB);
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
    public Double getTravelTime() {
        return travelTimeA + travelTimeB;
    }

    @Override
    public void setTravelTime(Double calculate) {
        //Do nothing
    }

    @Override
    public Location getCurrentDepot() {
        return currentDepot;
    }

    @Override
    public List<Location> getArc() {
        return arc;
    }

    @Override
    public List<OrderedRoute> getOrderedRoutes() {
        return orderedRoutes;
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
