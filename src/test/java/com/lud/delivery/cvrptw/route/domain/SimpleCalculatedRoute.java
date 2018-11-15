package com.lud.delivery.cvrptw.route.domain;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleCalculatedRoute implements CalculatedRoute{

    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;
    private Double travelTime;
    private SimpleLocation origin;
    private SimpleLocation destiny;

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

    public void setOrigin(SimpleLocation origin) {
        this.origin = origin;
    }

    public void setDestiny(SimpleLocation destiny) {
        this.destiny = destiny;
    }

    @Override
    public List<Location> getArc() {
        return null;
    }

}
