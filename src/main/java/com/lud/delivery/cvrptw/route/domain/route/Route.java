package com.lud.delivery.cvrptw.route.domain.route;

import java.time.LocalDateTime;

import com.lud.delivery.cvrptw.route.domain.location.Location;

public interface Route{

    LocalDateTime getPickupTime();

    LocalDateTime getDeliveryTime();

    Location getOrigin();

    Location getDestiny();

    boolean isSynthetic();

    default int routeHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDestiny() == null) ? 0 : getDestiny().hashCode());
        result = prime * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = prime * result + ((getPickupTime() == null) ? 0 : getPickupTime().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        return result;
    }

    default boolean equalsRoute(Route otherRoute) {
        return this.getOrigin().equals(otherRoute.getOrigin())
            && this.getDestiny().equals(otherRoute.getDestiny())

            && (this.getPickupTime() == null && otherRoute.getPickupTime() == null
                ||this.getPickupTime() != null && otherRoute.getPickupTime() != null
                    && this.getPickupTime().equals(otherRoute.getPickupTime()))

            && (this.getDeliveryTime() == null && otherRoute.getDeliveryTime() == null
                ||this.getDeliveryTime() != null && otherRoute.getDeliveryTime() != null
                    && this.getDeliveryTime().equals(otherRoute.getDeliveryTime()));
    }
}
