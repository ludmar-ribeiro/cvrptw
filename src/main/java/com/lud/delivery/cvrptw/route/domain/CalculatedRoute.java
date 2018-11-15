package com.lud.delivery.cvrptw.route.domain;

import java.util.List;

public interface CalculatedRoute extends Route {

    Double getTravelTime();

    void setTravelTime(Double calculate);

    List<Location> getArc();

    @Override
    default int routeHashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + Route.super.routeHashCode();
        for(Location location : getArc())
            result = prime * result + location.hashCode();
        return result;
    }

    @Override
    default boolean equalsRoute(Route otherRoute) {
        if (!Route.super.equalsRoute(otherRoute))
            return false;

        if (!(otherRoute instanceof CalculatedRoute))
            return false;

        CalculatedRoute otherCalculatedRoute = (CalculatedRoute) otherRoute;

        if (this.getArc().size() != otherCalculatedRoute.getArc().size())
            return false;

        for (int i = 0; i < this.getArc().size(); i++) {
            if (!this.getArc().get(i).equals(otherCalculatedRoute.getArc().get(i)))
                return false;
        }

        return true;
    }

}
