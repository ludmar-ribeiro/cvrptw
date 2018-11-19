package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRouteFactory;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.evaluator.PossibleRouteEvaluator;

/**
 * Route constraint
 *
 * This constraint accepts only new steps, when it is a depot, that has possible
 * next steps
 *
 * NOTE: This constraint applies only for depot as new step, if the new step is not
 *       a depot this constraint will be skipped (return true) 
 *
 * @author Ludmar Ribeiro
 */
@Component
public class DepotWithoutPossibleTargetConstraint implements RouteConstraint{

    /**
     * Possible routes evaluator
     */
    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    /**
     * Full route factory
     */
    @Autowired
    private FullCalculatedRouteFactory fullCalculatedRouteFactory;

    /**
     * Returns true when the candidate respect this constraint
     *
     * @param candidate
     * @param rootRoute
     * @param workset
     * @return boolean
     */
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

    /**
     * Simulates a full route using candidate as next step 
     *
     * @param workset
     * @param rootRoute
     * @param candidate
     * @return {@link CalculatedRoute}
     */
    private CalculatedRoute simulateRoute(RouteCalculationWorkset workset, CalculatedRoute rootRoute, CalculatedRoute candidate) {

        return fullCalculatedRouteFactory.of(rootRoute, candidate);
    }
}
