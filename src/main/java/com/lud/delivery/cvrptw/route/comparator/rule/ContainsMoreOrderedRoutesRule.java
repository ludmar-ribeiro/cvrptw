package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

@Component
@Order(1)
public class ContainsMoreOrderedRoutesRule implements SortingRule<CalculatedRoute> {

    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        Long numberOfOrderedRoutes2 = getNumberOfOrderedRoutes(route2);
        Long numberOfOrderedRoutes1 = getNumberOfOrderedRoutes(route1);

        return numberOfOrderedRoutes2.compareTo(numberOfOrderedRoutes1);
    }

    private Long getNumberOfOrderedRoutes(CalculatedRoute route) {
        return Long.valueOf(route.getOrderedRoutes().size());
    }

}
