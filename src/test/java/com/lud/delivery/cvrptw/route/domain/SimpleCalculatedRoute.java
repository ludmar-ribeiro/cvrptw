package com.lud.delivery.cvrptw.route.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.SubRoute;

/**
 * A POJO that implements the CalculatedRoute to be used as data test
 * by the Route Calculation related tests. 
 *
 * @author Ludmar Ribeiro
 *
 */
public class SimpleCalculatedRoute implements CalculatedRoute{

    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;
    private Double travelTime;
    private Location origin;
    private Location destiny;

    @Override
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    @Override
    public Double getTravelTime() {
        return travelTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setTravelTime(Double travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public Location getOrigin() {
        return origin;
    }

    @Override
    public Location getDestiny() {
        return destiny;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public void setDestiny(Location destiny) {
        this.destiny = destiny;
    }

    @Override
    public List<Location> getLocations() {
        return null;
    }

    @Override
    public boolean isSynthetic() {
        return true;
    }

    @Override
    public Location getCurrentDepot() {
        return null;
    }

    @Override
    public List<OrderedRoute> getOrderedRoutes() {
        return null;
    }

    @Override
    public OrderedRoute getLastDelivered() {
        return null;
    }

    @Override
    public Double getLateDeliveryTime() {
        return null;
    }

    @Override
    public void setLateDeliveryTime(Double lateDeliveryTime) {
    }

    @Override
    public LocalDateTime getCurrentDepotArrivalTime() {
        return null;
    }

    @Override
    public List<SubRoute> getSubRoutes() {
        return null;
    }

    @Override
    public SubRoute getOpenSubRoute() {
        return null;
    }
}
