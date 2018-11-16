package com.lud.delivery.cvrptw.route.component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.SynteticRoute;
import com.lud.delivery.cvrptw.route.domain.wrapper.CalculatedRouteWrapper;

@Component
public class SubRouteCalculator {

    @Autowired
    TravelTimeCalculator travelTimeCalculator;

    public List<CalculatedRoute> calculate(List<Route> routes) {
        return routes
                .stream()
                .map(r -> calculate(r))
                .collect(Collectors.toList());
    }

    public CalculatedRoute calculate(Route route) {
        if(route instanceof CalculatedRoute)
            return (CalculatedRoute) route;

        return calculate(new CalculatedRouteWrapper(route)); 
    }

    private CalculatedRoute calculate(CalculatedRoute route) {
        route.setTravelTime(travelTimeCalculator.calculate(route.getOrigin(), route.getDestiny()));

        return route;
    }

    public List<CalculatedRoute> calculate(RouteWorkset workset, Location origin, Location destiny) {
        return calculate(getRoutesFor(workset, origin, destiny));
    }

    private List<Route> getRoutesFor(RouteWorkset workset, Location origin, Location destiny) {
        if(!destiny.isDepot()) {

            return getRoutesForTarget(workset, origin, destiny);
        }

        return Collections.singletonList((Route) new SynteticRoute(origin, destiny, LocalDateTime.MIN, LocalDateTime.MAX));
    }

    private List<Route> getRoutesForTarget(RouteWorkset workset, Location origin, Location destiny) {
        List<CalculatedRoute> orderedRoutes = workset.getTargetToRouteMap().get(destiny);
        List<Route> routes = new LinkedList<>();

        for(CalculatedRoute orderedRoute: orderedRoutes) {

            if(origin.isDepot() && orderedRoute.getOrigin().equals(origin)) {
                routes.add(orderedRoute);
                continue;
            }

            routes.add(new SynteticRoute(origin, destiny, orderedRoute.getPickupTime(), orderedRoute.getDeliveryTime()));
        }

        return routes;
    }

}
