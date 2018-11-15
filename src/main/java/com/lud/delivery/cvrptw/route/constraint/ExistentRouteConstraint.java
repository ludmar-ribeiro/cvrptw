package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.component.SubRouteCalculator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;

@Component
public class ExistentRouteConstraint implements RouteConstraint{

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    @Override
    public boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset) {

        CalculatedRoute simulation = simulateRoute(workset, route, location);

        return !workset.getSortedRoutes().contains(simulation)
             && !workset.getSortedClosedRoutes().contains(simulation);
    }

    private CalculatedRoute simulateRoute(RouteWorkset workset, CalculatedRoute route, Location destiny) {

        return new CompositeCalculatedRoute(
                route,
                subRouteCalculator.calculate(workset, route.getDestiny(), destiny)
            );
    }
}
