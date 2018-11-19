package com.lud.delivery.cvrptw.route.constraint;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Route constraint
 *
 * This constraint accepts only new steps that don't will be picked up before 
 * its pickup time 
 *
 * NOTE: This constraint don't applies for depot as new step, if the new step is
 *       a depot this constraint will be skipped (return true) 
 *
 * @author Ludmar Ribeiro
 */
@Component
public class PickupTimeConstraint implements RouteConstraint{

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

        LocalDateTime currentDepotArrivalTime = DateTimeUtils
                .ignoreSeconds(rootRoute.getCurrentDepotArrivalTime());

        return !currentDepotArrivalTime
                .isBefore(DateTimeUtils
                               .ignoreSeconds(
                                       candidate.getPickupTime()));
    }
}
