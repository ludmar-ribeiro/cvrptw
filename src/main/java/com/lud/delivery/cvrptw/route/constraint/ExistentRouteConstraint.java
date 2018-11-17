package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.composite.CompositeCalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class ExistentRouteConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset) {

        CalculatedRoute simulation = simulateRoute(workset, rootRoute, candidate);

        return !workset
                .getRoutes()
                    .contains(simulation);
    }

    private CalculatedRoute simulateRoute(RouteCalculationWorkset workset, CalculatedRoute rootRoute, CalculatedRoute candidate) {

        return new CompositeCalculatedRoute(rootRoute, candidate);
    }
}
