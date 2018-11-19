package com.lud.delivery.cvrptw.route.calculator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.pointToPointRoute.PointToPointCalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.pointToPointRoute.PointToPointCalculatedRouteFactory;

@Component
public class RouteCalculator {

    @Autowired
    TravelTimeCalculator travelTimeCalculator;

    @Autowired
    PointToPointCalculatedRouteFactory factory;

    public List<CalculatedRoute> calculate(List<Route> routes) {
        return routes
                .stream()
                .map(r -> calculate(r))
                .collect(Collectors.toList());
    }

    public CalculatedRoute calculate(Route route) {
        if(route instanceof CalculatedRoute)
            return (CalculatedRoute) route;

        return factory.of(
                route, 
                travelTimeCalculator.calculate(route.getOrigin(), route.getDestiny())); 
    }
}
