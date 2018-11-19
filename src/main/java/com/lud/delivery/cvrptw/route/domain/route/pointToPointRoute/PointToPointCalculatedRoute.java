package com.lud.delivery.cvrptw.route.domain.route.pointToPointRoute;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;

public class PointToPointCalculatedRoute implements CalculatedRoute {

    private Location origin;
    private Location destiny;
    
    private LocalDateTime pickupTime;
    
    private LocalDateTime deliveryTime;

    private Double travelTime;

    private Location currentDepot;

    private List<Location> locations = new LinkedList<>();

    private List<OrderedRoute> orderedRoutes;

    private OrderedRoute lastDelivered;

    private Double lateDeliveryTime = Double.valueOf(0);

    private LocalDateTime currentDepotArrivalTime;

    protected PointToPointCalculatedRoute() {
    }

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

    @Override
    public Location getOrigin() {
        return origin;
    }

    @Override
    public Location getDestiny() {
        return destiny;
    }

    @Override
    public Location getCurrentDepot() {
        return currentDepot;
    }

    @Override
    public LocalDateTime getCurrentDepotArrivalTime() {
        return currentDepotArrivalTime;
    }

    @Override
    public List<OrderedRoute> getOrderedRoutes() {
        return orderedRoutes;
    }

    @Override
    public List<Location> getLocations() {
        return locations; 
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

    protected void setOrigin(Location origin) {
        this.origin = origin;
    }

    protected void setDestiny(Location destiny) {
        this.destiny = destiny;
    }

    protected void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    protected void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    protected void setTravelTime(Double travelTime) {
        this.travelTime = travelTime;
    }

    protected void setCurrentDepot(Location currentDepot) {
        this.currentDepot = currentDepot;
    }

    protected void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    protected void setOrderedRoutes(List<OrderedRoute> orderedRoutes) {
        this.orderedRoutes = orderedRoutes;
    }

    protected void setLastDelivered(OrderedRoute lastDelivered) {
        this.lastDelivered = lastDelivered;
    }

    protected void setCurrentDepotArrivalTime(LocalDateTime currentDepotArrivalTime) {
        this.currentDepotArrivalTime = currentDepotArrivalTime;
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