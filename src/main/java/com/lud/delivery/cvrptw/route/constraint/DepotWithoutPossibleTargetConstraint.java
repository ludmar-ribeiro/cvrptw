package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRouteFactory;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.evaluator.PossibleRouteEvaluator;

@Component
public class DepotWithoutPossibleTargetConstraint implements RouteConstraint{

    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    @Autowired
    private FullCalculatedRouteFactory fullCalculatedRouteFactory;

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset) {
        if(!candidate.getDestiny().isDepot())
            return true;

        CalculatedRoute simulation = simulateRoute(workset, rootRoute, candidate);

        return workset.getMap().get(candidate.getDestiny())
                        .stream()
                        .filter(r -> possibleRouteEvaluator.isPossible(r, simulation, workset))
                .count() > 0;
    }

    private CalculatedRoute simulateRoute(RouteCalculationWorkset workset, CalculatedRoute rootRoute, CalculatedRoute candidate) {

        return fullCalculatedRouteFactory.of(rootRoute, candidate);
    }
}
