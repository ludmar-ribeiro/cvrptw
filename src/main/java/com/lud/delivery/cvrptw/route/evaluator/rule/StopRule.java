package com.lud.delivery.cvrptw.route.evaluator.rule;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Stop rule
 *
 * A rule that determines if a calculation process is done
 *
 * @author Ludmar Ribeiro
 *
 */
public interface StopRule {

    /**
     * Verify if a calculation process is done.
     *
     * @param workset
     * @param route
     * @return boolean, true when the calculation process is done
     */
    boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route);
}
