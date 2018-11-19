package com.lud.delivery.cvrptw.route.evaluator.rule;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Stop rule
 *
 * This rule determines a calculation process done when there is 
 * no open route to be processed
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class NoOpenRoutesRule implements StopRule {

    /**
     * Verify if a calculation process is done.
     *
     * @param workset
     * @param route
     * @return boolean, true when the calculation process is done
     */
    @Override
    public boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route) {
        return workset.getSortedOpenRoutes().isEmpty();
    }
}
