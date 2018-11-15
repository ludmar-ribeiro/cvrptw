package com.lud.delivery.cvrptw.route.component;

import java.time.LocalDateTime;
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

    private Route getRouteFor(RouteWorkset workset, Location origin, Location destiny) {
        LocalDateTime pickup = null;
        LocalDateTime delivery = null;

        if(!destiny.isDepot()) {
            CalculatedRoute orderedRoute = workset.getTargetToRouteMap().get(destiny);

            if(origin.isDepot() && origin.equals(orderedRoute.getOrigin()))
                return orderedRoute;

            pickup = orderedRoute.getPickupTime();
            delivery = orderedRoute.getDeliveryTime();
        }

        return new SynteticRoute(origin, destiny, pickup, delivery);
    }

    public CalculatedRoute calculate(RouteWorkset workset, Location origin, Location destiny) {
        return calculate(getRouteFor(workset, origin, destiny));
    }
}
