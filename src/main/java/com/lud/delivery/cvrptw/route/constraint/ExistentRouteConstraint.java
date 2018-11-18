package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRouteFactory;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class ExistentRouteConstraint implements RouteConstraint{

    @Autowired
    private FullCalculatedRouteFactory fullCalculatedRouteFactory;

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset) {

        CalculatedRoute simulation = simulateRoute(workset, rootRoute, candidate);

        return !workset
                .getRoutes()
                    .contains(simulation);
    }

    private CalculatedRoute simulateRoute(RouteCalculationWorkset workset, CalculatedRoute rootRoute, CalculatedRoute candidate) {

        return fullCalculatedRouteFactory.of(rootRoute, candidate);
    }
}
