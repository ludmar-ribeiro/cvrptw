package com.lud.delivery.cvrptw.route.domain.route.fullRoute;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

@Component
public class FullCalculatedRouteFactory {
    public FullCalculatedRoute of(CalculatedRoute routeA, CalculatedRoute routeB) {

        FullCalculatedRoute fullRoute = new FullCalculatedRoute(); 

        evaluateAttributes(fullRoute, routeA, routeB);

        return fullRoute;
    }

    private void evaluateAttributes(FullCalculatedRoute fullRoute, CalculatedRoute routeA, CalculatedRoute routeB) {

        fullRoute.setOrigin(routeA.getOrigin());
        fullRoute.setDestiny(routeB.getDestiny());

        fullRoute.setTravelTime(routeA.getTravelTime() + routeB.getTravelTime());

        fullRoute.setPickupTime(routeA.getPickupTime());
        fullRoute.setDeliveryTime(DateTimeUtils.addMilliseconds(routeA.getDeliveryTime(), routeB.getTravelTime()));

        evaluateCurrentDepot(fullRoute, routeA, routeB);
        evaluateCurrentDepotArrivalTime(fullRoute, routeA, routeB);

        fullRoute.getLocations().addAll(routeA.getLocations());
        fullRoute.getLocations().add(routeB.getDestiny());

        fullRoute.getOrderedRoutes().addAll(routeA.getOrderedRoutes());
        fullRoute.getOrderedRoutes().addAll(routeB.getOrderedRoutes());

        evaluateLastDelivered(fullRoute, routeA, routeB);

        fullRoute.setLateDeliveryTime(routeA.getLateDeliveryTime());
    }

    private void evaluateLastDelivered(FullCalculatedRoute fullRoute, CalculatedRoute routeA, CalculatedRoute routeB) {
        if(routeB.getLastDelivered() != null) {
            fullRoute.setLastDelivered(routeB.getLastDelivered());
            return;
        }

        fullRoute.setLastDelivered(routeA.getLastDelivered());
    }

    private void evaluateCurrentDepot(FullCalculatedRoute fullRoute, CalculatedRoute routeA, CalculatedRoute routeB) {

        if(routeB.getCurrentDepot() != null) {
            fullRoute.setCurrentDepot(routeB.getCurrentDepot());
            return;
        }

        fullRoute.setCurrentDepot(routeA.getCurrentDepot());
    }

    private void evaluateCurrentDepotArrivalTime(FullCalculatedRoute fullRoute, CalculatedRoute routeA, CalculatedRoute routeB) {
        if(fullRoute.getDestiny().isDepot()) {
            fullRoute.setCurrentDepotArrivalTime(fullRoute.getDeliveryTime());
            return;
        }

        fullRoute.setCurrentDepotArrivalTime(routeA.getCurrentDepotArrivalTime());
    }
}
