package com.lud.delivery.cvrptw.route.domain;

import java.time.LocalDateTime;

public class SynteticRoute implements Route {

    private Location origin;
    private Location destiny;
    private LocalDateTime delivery;
    private LocalDateTime pickup;

    public SynteticRoute(Location origin, Location destiny, LocalDateTime pickup, LocalDateTime delivery) {
        this.origin = origin;
        this.destiny = destiny;
        this.pickup = pickup;
        this.delivery = delivery;
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
}
