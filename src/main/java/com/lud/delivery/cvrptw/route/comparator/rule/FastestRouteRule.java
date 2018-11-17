package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

@Component
@Order(4)
public class FastestRouteRule implements SortingRule<CalculatedRoute>{

    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        return route1.getTravelTime().compareTo(route2.getTravelTime());
    }
}
