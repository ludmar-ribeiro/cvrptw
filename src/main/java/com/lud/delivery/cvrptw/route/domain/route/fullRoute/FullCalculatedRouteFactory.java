package com.lud.delivery.cvrptw.route.domain.route.fullRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.factory.SubRouteFactory;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.SubRoute;

/**
 * {@link FullCalculatedRoute} Factory
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class FullCalculatedRouteFactory {

    /**
     * SubRoute Factory
     */
    @Autowired
    private SubRouteFactory subRouteFatory;

    /**
     * Creates a {@link FullCalculatedRoute} from two routes 
     *
     * @param routeA
     * @param routeB
     * @return {@link FullCalculatedRoute}
     */
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

        evaluateSubRoutes(fullRoute, routeA, routeB);
    }

    private void evaluateSubRoutes(FullCalculatedRoute fullRoute, CalculatedRoute routeA, CalculatedRoute routeB) {
        fullRoute.getSubRoutes().addAll(routeA.getSubRoutes());

        SubRoute open = cloneOpenSubRoute(fullRoute, routeA);

        if(routeB.getDestiny().isDepot()) {
            open = subRouteFatory.of(routeB.getDestiny());
            fullRoute.getSubRoutes().add(open);
        }

        if(!routeB.getDestiny().isDepot())
            open.addOrderedRoute(routeB.getLastDelivered());

        fullRoute.setOpenSubRoute(open);
    }

    private SubRoute cloneOpenSubRoute(FullCalculatedRoute fullRoute, CalculatedRoute routeA) {
        fullRoute.getSubRoutes().remove(routeA.getOpenSubRoute());

        SubRoute open = subRouteFatory.of(routeA.getCurrentDepot());
        routeA.getOpenSubRoute().getOrderedRoutes().forEach(open::addOrderedRoute);
        fullRoute.getSubRoutes().add(open);
        return open;
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
