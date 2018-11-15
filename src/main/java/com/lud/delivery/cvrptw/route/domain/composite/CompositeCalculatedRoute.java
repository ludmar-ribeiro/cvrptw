package com.lud.delivery.cvrptw.route.domain.composite;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;

public class CompositeCalculatedRoute implements CalculatedRoute {


    private CalculatedRoute routeA;
    private CalculatedRoute routeB;

    public CompositeCalculatedRoute(CalculatedRoute routeA, CalculatedRoute routeB) {
        this.routeA = routeA;
        this.routeB = routeB;
    }

    @Override
    public LocalDateTime getPickupTime() {
        return routeA.getPickupTime();
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return null;
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
    }

    @Override
    public List<Location> getArc() {
        return Stream.concat(
                    routeA.getArc().stream(), 
                    Collections.singleton(getDestiny()).stream())
                .collect(Collectors.toList());
    }

}
