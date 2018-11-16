package com.lud.delivery.cvrptw.route.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.CalculatedRouteMap;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.SyntheticRoute;

@Component
public class PreRouteCalculator {

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    public CalculatedRouteMap calculate(List<Route> routes) {

        CalculatedRouteMap routeMap = new CalculatedRouteMap();

        routeMap.putAll(subRouteCalculator.calculate(routes));

        for(Route routeA: routes) {
            for(Route routeB: routes) {
                createRoutes(routeMap, routeA, routeB); 
            }
        }

        return routeMap;
    }

    private void createRoutes(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        createRouteDestinyToDestiny(routeMap, routeA, routeB);
        createRouteDestinyToOrigin(routeMap, routeA, routeB);
    }


    private void createRouteDestinyToDestiny(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        if(routeA.getDestiny().equals(routeB.getDestiny()))
            return;

        if(!routeA.getOrigin().equals(routeB.getOrigin()))
            return;

        Route route = new SyntheticRoute(routeA.getDestiny(), routeB.getDestiny(), routeB.getPickupTime(), routeB.getDeliveryTime());

        calculateRoute(routeMap, route);
    }

    private void createRouteDestinyToOrigin(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        Route route = new SyntheticRoute(routeA.getDestiny(), routeB.getOrigin(), LocalDateTime.MIN, LocalDateTime.MAX);

        calculateRoute(routeMap, route);
    }

    private void calculateRoute(CalculatedRouteMap routeMap, Route route) {
        CalculatedRoute calculatedRoute = subRouteCalculator.calculate(route);

        if(!routeMap.get(calculatedRoute.getOrigin(), calculatedRoute.getDestiny()).contains(calculatedRoute))
            routeMap.put(calculatedRoute);
    }
}
