package com.lud.delivery.cvrptw.route.evaluator.rule;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

public interface StopRule {

    boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route);
}
