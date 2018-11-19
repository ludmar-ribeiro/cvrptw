package com.lud.delivery.cvrptw.route.domain.route;

import java.time.LocalDateTime;
import java.util.List;

import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Calculated Route 
 *
 * @author Ludmar Ribeiro
 *
 */
public interface CalculatedRoute extends SyntheticRoute {

    /**
     * Returns the travel time of the route
     *
     * @return {@link Double}
     */
    Double getTravelTime();

    /**
     * Return the {@link List} of {@link Location} that a route
     * passes by
     *
     * @return {@link List} of {@link Location}
     */
    List<Location> getLocations();

    /**
     * Returns the current depot that route get the supplies 
     *
     * @return {@link Location}
     */
    Location getCurrentDepot();

    /**
     * Returns the time when the current depot was arrived
     *
     * @return {@link LocalDateTime}
     */
    LocalDateTime getCurrentDepotArrivalTime();

    /**
     * Returns the last {@link OrderedRoute} delivered
     *
     * @return {@link OrderedRoute}
     */
    OrderedRoute getLastDelivered();

    /**
     * Returns the late delivery time
     *
     * @return {@link Double}
     */
    Double getLateDeliveryTime();

    /**
     * Returns the {@link List} of {@link SubRoute}
     * 
     * @return {@link List} of {@link SubRoute}
     */
    List<SubRoute> getSubRoutes();

    /**
     * Return the current open {@link SubRoute}
     *
     * @return {@link SubRoute} 
     */
    SubRoute getOpenSubRoute();

    /**
     * Sets the late delivery time
     * @param lateDeliveryTime
     */
    void setLateDeliveryTime(Double lateDeliveryTime);

    /**
     * Default hash code for calculated route
     * @return int
     */
    @Override
    default int routeHashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + SyntheticRoute.super.routeHashCode();
        for(Location location : getLocations())
            result = prime * result + location.hashCode();
        return result;
    }

    /**
     * default equals logic for calculated routes
     * @param otherRoute
     * @return boolean, true when equals
     */
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
