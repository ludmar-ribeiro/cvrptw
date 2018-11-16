package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.component.PossibleRouteEvaluator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;

@Component
public class DepotWithoutPossibleTargetConstraint implements RouteConstraint{

    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteWorkset workset) {
        if(!candidate.getDestiny().isDepot())
            return true;

        CalculatedRoute simulation = simulateRoute(workset, rootRoute, candidate);

        return workset.getMap().get(candidate.getDestiny())
                        .stream()
                        .filter(r -> possibleRouteEvaluator.isPossible(r, simulation, workset))
                .count() > 0;
    }

    private CalculatedRoute simulateRoute(RouteWorkset workset, CalculatedRoute rootRoute, CalculatedRoute candidate) {

        return new CompositeCalculatedRoute(rootRoute, candidate);
    }
}
