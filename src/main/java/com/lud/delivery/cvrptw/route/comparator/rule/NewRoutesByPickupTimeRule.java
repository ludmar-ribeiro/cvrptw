package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

@Component
@Order(2)
public class NewRoutesByPickupTimeRule implements SortingRule<CalculatedRoute>{

    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        Long numberOfOrderedRoutes2 = getNumberOfOrderedRoutes(route2);
        Long numberOfOrderedRoutes1 = getNumberOfOrderedRoutes(route1);

        if(numberOfOrderedRoutes1.equals(Long.valueOf(1)) && numberOfOrderedRoutes2.equals(Long.valueOf(1)))
            return DateTimeUtils.ignoreMilliseconds(route1.getPickupTime()).compareTo(DateTimeUtils.ignoreMilliseconds(route2.getPickupTime()));

        return 0;
    }

    private Long getNumberOfOrderedRoutes(CalculatedRoute route) {
        return Long.valueOf(route.getOrderedRoutes().size());
    }
}
