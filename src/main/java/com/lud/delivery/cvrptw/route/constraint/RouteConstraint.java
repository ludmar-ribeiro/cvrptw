package com.lud.delivery.cvrptw.route.constraint;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Route constraint
 *
 * @author Ludmar Ribeiro
 */
public interface RouteConstraint {

    /**
     * Returns true when the candidate respect this constraint
     *
     * @param candidate
     * @param rootRoute
     * @param workset
     * @return boolean
     */
    boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset);
}
