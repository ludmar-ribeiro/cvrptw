package com.lud.delivery.cvrptw.route.evaluator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.constraint.RouteConstraint;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Possible routes evaluator
 *
 * Component that verify if a next step is possible
 *
 * This component uses the {@link List} of {@link RouteConstraint}
 * to verify the possible routes, a route will be evaluated as possible
 * when and only when all {@link RouteConstraint} returns true
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class PossibleRouteEvaluator {

    /**
     * {@link List} of {@link RouteConstraint}
     */
    @Autowired
    private List<RouteConstraint> constraints;

    /**
     * Verify if a candidate of next step is possible
     *
     * @param candidate
     * @param rootRoute
     * @param workset
     * @return boolean, true when is possible
     */
    public boolean isPossible(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset) {

        return constraints
                .stream()
                    .map(c -> c.isAllowed(candidate, rootRoute, workset))
                    .reduce((b1, b2) -> b1 && b2)
            .orElse(true); 
    }
}
