package com.lud.delivery.cvrptw.route.domain.route;

import java.time.LocalDateTime;
import java.util.List;

import com.lud.delivery.cvrptw.route.domain.location.Location;

public interface CalculatedRoute extends SyntheticRoute {

    Double getTravelTime();

    List<Location> getLocations();

    Location getCurrentDepot();
    LocalDateTime getCurrentDepotArrivalTime();

    OrderedRoute getLastDelivered();

    Double getLateDeliveryTime();

    void setLateDeliveryTime(Double lateDeliveryTime);

    @Override
    default int routeHashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + SyntheticRoute.super.routeHashCode();
        for(Location location : getLocations())
            result = prime * result + location.hashCode();
        return result;
    }

    @Override
    default boolean equalsRoute(Route otherRoute) {
        if (!SyntheticRoute.super.equalsRoute(otherRoute))
            return false;

        if (!(otherRoute instanceof CalculatedRoute))
            return false;

        CalculatedRoute otherCalculatedRoute = (CalculatedRoute) otherRoute;

        if (this.getLocations().size() != otherCalculatedRoute.getLocations().size())
            return false;

        for (int i = 0; i < this.getLocations().size(); i++) {
            if (!this.getLocations().get(i).equals(otherCalculatedRoute.getLocations().get(i)))
                return false;
        }

        return true;
    }



}
