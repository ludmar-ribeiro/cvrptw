package com.lud.delivery.cvrptw.route.evaluator.rule;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class RouteCoveredAllOrderedRoutesRule implements StopRule{

    @Override
    public boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route) {
        return route.getOrderedRoutes().size() == workset.getMap().getOrderedRoutes().size();
    }
}
