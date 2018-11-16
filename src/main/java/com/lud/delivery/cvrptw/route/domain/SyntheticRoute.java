package com.lud.delivery.cvrptw.route.domain;

import java.time.LocalDateTime;

public class SyntheticRoute implements Route {

    private Location origin;
    private Location destiny;
    private LocalDateTime delivery;
    private LocalDateTime pickup;

    public SyntheticRoute(Location origin, Location destiny, LocalDateTime pickup, LocalDateTime delivery) {
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

        Route other = (Route) obj;

        return this.equalsRoute(other);
    }

}
