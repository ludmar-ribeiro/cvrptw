package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Route constraint
 *
 * This constraint accepts only new steps that don't increments
 * the shipment cargo more than its capacity
 *
 * NOTE: This constraint don't applies for depot as new step, if the new step is
 *       a depot this constraint will be skipped (return true) 
 *
 * @author Ludmar Ribeiro
 */
@Component
public class CapacityConstraint implements RouteConstraint{

    /**
     * Vehicle fixed capacity
     */
    @Value("${route.vehicle.capacity}")
    private Integer capacity;

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
        if(candidate.getDestiny().isDepot())
            return true;

        if(rootRoute.getLocations().size() < capacity)
            return true;

        return rootRoute
                .getLocations()
                    .stream()
                        .skip(rootRoute.getLocations().size() - capacity)
                        .filter(l -> !l.isDepot())
               .count() < capacity;
    }

}
