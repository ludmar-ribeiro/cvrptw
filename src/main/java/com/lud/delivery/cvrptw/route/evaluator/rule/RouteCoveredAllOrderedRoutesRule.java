package com.lud.delivery.cvrptw.route.evaluator.rule;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Stop rule
 *
 * This rule determines a calculation process done when a close route covers
 * all the ordered routes
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class RouteCoveredAllOrderedRoutesRule implements StopRule{

    /**
     * Verify if a calculation process is done.
     *
     * @param workset
     * @param route
     * @return boolean, true when the calculation process is done
     */
    @Override
    public boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route) {
        return route.getOrderedRoutes().size() == workset.getMap().getOrderedRoutes().size();
    }
}
