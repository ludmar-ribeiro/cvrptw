package com.lud.delivery.cvrptw.route.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRouteMap;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.wrapper.SyntheticRouteWrapper;

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

        Route route = new SyntheticRouteWrapper(routeA.getDestiny(), (OrderedRoute) routeB);

        calculateRoute(routeMap, route);
    }

    private void createRouteDestinyToOrigin(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        Route route = new SyntheticRouteWrapper(routeA.getDestiny(), routeB.getOrigin());

        calculateRoute(routeMap, route);
    }

    private void calculateRoute(CalculatedRouteMap routeMap, Route route) {
        CalculatedRoute calculatedRoute = subRouteCalculator.calculate(route);

        if(!routeMap.get(calculatedRoute.getOrigin(), calculatedRoute.getDestiny()).contains(calculatedRoute))
            routeMap.put(calculatedRoute);
    }
}
