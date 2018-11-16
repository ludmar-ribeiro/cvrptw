package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;

@Component
public class ExistentRouteConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteWorkset workset) {

        CalculatedRoute simulation = simulateRoute(workset, rootRoute, candidate);

        return !workset
                .getSortedRoutes()
                    .contains(simulation);
    }

    private CalculatedRoute simulateRoute(RouteWorkset workset, CalculatedRoute rootRoute, CalculatedRoute candidate) {

        return new CompositeCalculatedRoute(rootRoute, candidate);
    }
}
