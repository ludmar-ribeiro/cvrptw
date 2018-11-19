package com.lud.delivery.cvrptw.route.depreciator.rule;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Rule that adds some depreciating value to route
 *
 * @author Ludmar Ribeiro
 *
 */
public interface DepreciationRule {

    /**
     * Adds depreciating values to a route
     *
     * @param workset
     * @param route
     */
    void depreciate(RouteCalculationWorkset workset, CalculatedRoute route);

}
