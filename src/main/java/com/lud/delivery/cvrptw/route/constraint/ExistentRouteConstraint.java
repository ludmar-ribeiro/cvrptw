package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRouteFactory;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Route constraint
 *
 * This constraint accepts only new steps that don't generates a existent full route
 *
 * @author Ludmar Ribeiro
 */
@Component
public class ExistentRouteConstraint implements RouteConstraint{

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

        CalculatedRoute simulation = simulateRoute(workset, rootRoute, candidate);

        return !workset
                .getRoutes()
                    .contains(simulation);
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
