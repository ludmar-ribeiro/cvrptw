package com.lud.delivery.cvrptw.route.domain.route.pointToPointRoute;

import java.util.Collections;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.SyntheticRoute;

@Component
public class PointToPointCalculatedRouteFactory {

    public PointToPointCalculatedRoute of(Route route, Double travelTime) {

        PointToPointCalculatedRoute calculatedRoute = new PointToPointCalculatedRoute();

        evaluateAttributes(calculatedRoute, route, travelTime);

        return calculatedRoute;
    }

    private void evaluateAttributes(PointToPointCalculatedRoute calculatedRoute, Route route, Double travelTime) {
        calculatedRoute.setDestiny(route.getDestiny());
        calculatedRoute.setOrigin(route.getOrigin());

        calculatedRoute.setPickupTime(route.getPickupTime());
        evaluateDeliveryTime(calculatedRoute, route, travelTime);

        calculatedRoute.setTravelTime(travelTime);

        calculatedRoute.getLocations().add(route.getOrigin());
        calculatedRoute.getLocations().add(route.getDestiny());

        evaluateCurrentDepot(calculatedRoute, route);
        evaluateOrderedRoutes(calculatedRoute, route);
        evaluateLastDelivered(calculatedRoute);
        evaluateCurrentDepotArrivalTime(calculatedRoute, route);
    }

    private void evaluateDeliveryTime(PointToPointCalculatedRoute calculatedRoute, Route route, Double travelTime) {
        if(route.getDestiny().isDepot())
            calculatedRoute.setDeliveryTime(route.getDeliveryTime()); 

        calculatedRoute.setDeliveryTime(
                DateTimeUtils.addMilliseconds(route.getPickupTime(), travelTime));
    }

    private void evaluateCurrentDepotArrivalTime(PointToPointCalculatedRoute calculatedRoute, Route route) {
        if(route.getDestiny().isDepot()) {
            calculatedRoute.setCurrentDepotArrivalTime(route.getDeliveryTime());
            return;
        }

        calculatedRoute.setCurrentDepotArrivalTime(route.getPickupTime());
    }

    private void evaluateCurrentDepot(PointToPointCalculatedRoute calculatedRoute, Route route) {
        if(route.getDestiny().isDepot()) {
            calculatedRoute.setCurrentDepot(route.getDestiny());
            return;
        }

        if(route.getOrigin().isDepot())
            calculatedRoute.setCurrentDepot(route.getOrigin());
    }

    private void evaluateOrderedRoutes(PointToPointCalculatedRoute calculatedRoute, Route route) {
        if(route.isSynthetic()) {
            calculatedRoute.setOrderedRoutes(((SyntheticRoute) route).getOrderedRoutes());
            return;
        }

        calculatedRoute.setOrderedRoutes(Collections.singletonList((OrderedRoute) route));
    }

    private void evaluateLastDelivered(PointToPointCalculatedRoute calculatedRoute) {
        if(!calculatedRoute.getOrderedRoutes().isEmpty())
            calculatedRoute.setLastDelivered(calculatedRoute.getOrderedRoutes().get(0));
    }

}
