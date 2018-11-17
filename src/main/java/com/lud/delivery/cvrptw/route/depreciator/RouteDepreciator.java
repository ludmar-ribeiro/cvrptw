package com.lud.delivery.cvrptw.route.depreciator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.depreciator.rule.DepreciationRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class RouteDepreciator {

    @Autowired
    private List<DepreciationRule> rules;

    public void depreciate(RouteCalculationWorkset workset, CalculatedRoute route) {
        rules.forEach(r -> r.depreciate(workset, route));
    }
}
