package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

@Component
@Order(3)
public class NewRoutesByDeliveryTimeRule implements SortingRule<CalculatedRoute>{

    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        if(route1.getDestiny().isDepot() || route2.getDestiny().isDepot())
            return 0;


        Long numberOfOrderedRoutes2 = getNumberOfOrderedRoutes(route2);
        Long numberOfOrderedRoutes1 = getNumberOfOrderedRoutes(route1);

        if(numberOfOrderedRoutes1.equals(Long.valueOf(1)) && numberOfOrderedRoutes2.equals(Long.valueOf(1)))
            return DateTimeUtils
                    .ignoreSeconds(
                            route1
                                .getLastDelivered()
                                    .getDeliveryTime())
                    .compareTo(
                            DateTimeUtils
                                .ignoreSeconds(
                                        route2
                                            .getLastDelivered()
                                                .getDeliveryTime()));

        return 0;
    }

    private Long getNumberOfOrderedRoutes(CalculatedRoute route) {
        return Long.valueOf(route.getOrderedRoutes().size());
    }
}
