package com.lud.delivery.cvrptw.route.evaluator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.evaluator.rule.StopRule;

@Component
public class StopEvaluator {

    @Autowired
    private List<StopRule> stopRules;

    public boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route) {
        return stopRules
                .stream()
                    .map(r -> r.shouldStop(workset, route))
                    .reduce((b1, b2) -> b1 || b2)
                .orElse(false);
    }
}
