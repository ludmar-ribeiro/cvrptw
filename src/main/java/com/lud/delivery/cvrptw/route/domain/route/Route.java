package com.lud.delivery.cvrptw.route.domain.route;

import java.time.LocalDateTime;

import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Route
 *
 * @author Ludmar Ribeiro
 *
 */
public interface Route{

    /**
     * Pickup time
     *
     * @return {@link LocalDateTime}
     */
    LocalDateTime getPickupTime();

    /**
     * Delivery time
     *
     * @return {@link LocalDateTime}
     */
    LocalDateTime getDeliveryTime();

    /**
     * Origin Location
     *
     * @return {@link Location}
     */
    Location getOrigin();

    /**
     * Destiny Location
     *
     * @return {@link Location}
     */
    Location getDestiny();

    /**
     * Return true when the  route is synthetic
     *
     * @return boolean, true when is synthetic
     */
    boolean isSynthetic();

    /**
     * Default hash code for route
     * @return int
     */
    default int routeHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDestiny() == null) ? 0 : getDestiny().hashCode());
        result = prime * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = prime * result + ((getPickupTime() == null) ? 0 : getPickupTime().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        return result;
    }

    /**
     * default equals logic for routes
     * @param otherRoute
     * @return boolean, true when equals
     */
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
