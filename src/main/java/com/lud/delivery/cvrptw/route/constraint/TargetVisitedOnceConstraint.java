package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Route constraint
 *
 * This constraint accepts only new steps that was not covered yet
 *
 * NOTE: This constraint don't applies for depot as new step, if the new step is
 *       a depot this constraint will be skipped (return true) 
 *
 * @author Ludmar Ribeiro
 */
@Component
public class TargetVisitedOnceConstraint implements RouteConstraint{

    /**
     * Returns true when the candidate respect this constraint
     *
     * @param candidate
     * @param rootRoute
     * @param workset
     * @return boolean
     */
    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute routeRoute, RouteCalculationWorkset workset) {
        return candidate.getDestiny().isDepot() 
                || !routeRoute.getOrderedRoutes()
                        .contains(candidate.getLastDelivered());
    }
}
