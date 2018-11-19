package com.lud.delivery.cvrptw.route.calculator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.pointToPointRoute.PointToPointCalculatedRouteFactory;

/**
 * Component that calculates point-to-point routes
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class RouteCalculator {

    /**
     * Travel time calculator
     */
    @Autowired
    TravelTimeCalculator travelTimeCalculator;

    /**
     * Point-to-point calculated route factory
     */
    @Autowired
    PointToPointCalculatedRouteFactory factory;

    /**
     * Calculates a {@link List} of point-to-point {@link Route}
     *
     * @param routes
     * @return {@link List} of {@link CalculatedRoute}
     */
    public List<CalculatedRoute> calculate(List<Route> routes) {
        return routes
                .stream()
                .map(r -> calculate(r))
                .collect(Collectors.toList());
    }

    /**
     * Calculates a point-to-point route
     *
     * @param route
     * @return {@link CalculatedRoute}
     */
    public CalculatedRoute calculate(Route route) {
        if(route instanceof CalculatedRoute)
            return (CalculatedRoute) route;

        return factory.of(
                route, 
                travelTimeCalculator.calculate(route.getOrigin(), route.getDestiny())); 
    }
}
