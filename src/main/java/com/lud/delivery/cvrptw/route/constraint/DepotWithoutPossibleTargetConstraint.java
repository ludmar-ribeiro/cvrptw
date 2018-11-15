package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.component.PossibleRouteEvaluator;
import com.lud.delivery.cvrptw.route.component.SubRouteCalculator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;

@Component
public class DepotWithoutPossibleTargetConstraint implements RouteConstraint{

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    @Override
    public boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset) {
        if(!location.isDepot())
            return true;

        CalculatedRoute simulation = simulateRoute(workset, route, location);

        return workset
                .getDepotToTargetMap()
                    .get(location)
                        .stream()
                        .filter(l -> possibleRouteEvaluator.isPossible(l, simulation, workset))
                .count() > 0;
    }

    private CalculatedRoute simulateRoute(RouteWorkset workset, CalculatedRoute route, Location destiny) {

        return new CompositeCalculatedRoute(
                route,
                subRouteCalculator.calculate(workset, route.getDestiny(), destiny)
            );
    }
}
