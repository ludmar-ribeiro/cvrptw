package com.lud.delivery.cvrptw.route.depreciator.rule;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

public interface DepreciationRule {

    void depreciate(RouteCalculationWorkset workset, CalculatedRoute route);

}
