package com.lud.delivery.cvrptw.route.depreciator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.depreciator.rule.DepreciationRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Component that will add depreciating values to a route
 * according to a {@link List} of {@link DepreciationRule}
 * 
 * @author Ludmar Ribeiro
 *
 */
@Component
public class RouteDepreciator {

    /**
     * Depreciation rules
     */
    @Autowired
    private List<DepreciationRule> rules;

    /**
     * Adds depreciating values to a route
     *
     * @param workset
     * @param route
     */
    public void depreciate(RouteCalculationWorkset workset, CalculatedRoute route) {
        rules.forEach(r -> r.depreciate(workset, route));
    }
}
