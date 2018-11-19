package com.lud.delivery.cvrptw.route.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.map.CalculatedRouteMap;
import com.lud.delivery.cvrptw.route.domain.route.wrapper.SyntheticRouteWrapper;

/**
 * Component that calculates all point-to-point routes for a list of ordered routes  
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class PreRouteCalculator {

    /**
     * Point-to-point route calculator
     */
    @Autowired
    private RouteCalculator routeCalculator;

    /**
     * Calculates all point-to-point routes for a list of ordered routes
     *
     * @param routes
     * @return {@link CalculatedRouteMap}
     */
    public CalculatedRouteMap calculate(List<Route> routes) {

        CalculatedRouteMap routeMap = new CalculatedRouteMap();

        //Calculates all point-to-point routes for a list of ordered routes
        //and put it into the map 
        routeMap.putAll(routeCalculator.calculate(routes));

        //Calculates all point-to-point crossing locations for a list of ordered routes
        for(Route routeA: routes) {
            for(Route routeB: routes) {
                createRoutes(routeMap, routeA, routeB); 
            }
        }

        return routeMap;
    }

    /**
     * Calculates all point-to-point crossing locations on two ordered routes
     *
     * @param routeMap
     * @param routeA
     * @param routeB
     */
    private void createRoutes(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        createRouteDestinyToDestiny(routeMap, routeA, routeB);
        createRouteDestinyToOrigin(routeMap, routeA, routeB);
    }


    /**
     * Calculates a point-to-point crossing destiny locations of two ordered routes
     *
     * @param routeMap
     * @param routeA
     * @param routeB
     */
    private void createRouteDestinyToDestiny(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        //Can't calculate distance between same location
        if(routeA.getDestiny().equals(routeB.getDestiny()))
            return;

        //Can't calculate distance for ordered routes from different depots
        if(!routeA.getOrigin().equals(routeB.getOrigin()))
            return;

        //Creates a synthetic route for the two locations
        Route route = new SyntheticRouteWrapper(routeA.getDestiny(), (OrderedRoute) routeB);

        //Calculates a point-to-point route
        calculateRoute(routeMap, route);
    }

    /**
     * Calculates a point-to-point crossing destiny with origin locations of two ordered routes
     *
     * @param routeMap
     * @param routeA
     * @param routeB
     */
    private void createRouteDestinyToOrigin(CalculatedRouteMap routeMap, Route routeA, Route routeB) {
        //Creates a synthetic route for the two locations
        Route route = new SyntheticRouteWrapper(routeA.getDestiny(), routeB.getOrigin());

        //Calculates a point-to-point route
        calculateRoute(routeMap, route);
    }

    /**
     * Calculates a point-to-point route
     *
     * @param routeMap
     * @param route
     */
    private void calculateRoute(CalculatedRouteMap routeMap, Route route) {
        CalculatedRoute calculatedRoute = routeCalculator.calculate(route);

        if(!routeMap.get(calculatedRoute.getOrigin(), calculatedRoute.getDestiny()).contains(calculatedRoute))
            routeMap.put(calculatedRoute);
    }
}
