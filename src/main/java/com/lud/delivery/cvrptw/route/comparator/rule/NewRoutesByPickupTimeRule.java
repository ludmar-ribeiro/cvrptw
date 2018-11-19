package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

/**
 * Sorting rule for calculated routes
 *
 * This rule sorts the calculated routes 
 * by ascending pickup time of the route
 *
 * NOTE: This rule is valid only to compare two new ordered routes,
 *       so if any route that has more or less than one ordered route
 *       or ends to a depot, this rule will be skipped (return 0)
 *
 * NOTE: For these sorting rules the order matters as relevance
 *       please check the @Order annotation values for each rule
 *       to make sure the relevance is correct 
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
@Order(2)
public class NewRoutesByPickupTimeRule implements SortingRule<CalculatedRoute>{

    /**
     * Compares two calculated routes according this rule
     *
     * @param route1
     * @param route1
     * @return int, 1, -1 or 0 
     */
    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        if(route1.getDestiny().isDepot() || route2.getDestiny().isDepot())
            return 0;

        Long numberOfOrderedRoutes2 = getNumberOfOrderedRoutes(route2);
        Long numberOfOrderedRoutes1 = getNumberOfOrderedRoutes(route1);

        if(numberOfOrderedRoutes1.equals(Long.valueOf(1)) && numberOfOrderedRoutes2.equals(Long.valueOf(1)))
            return DateTimeUtils.ignoreSeconds(
                            route1.getPickupTime())
                    .compareTo(DateTimeUtils.ignoreSeconds(
                            route2.getPickupTime()));

        return 0;
    }

    /**
     * Gets the number of ordered routes covered by a route
     *
     * @param route
     * @return {@link Long}
     */
    private Long getNumberOfOrderedRoutes(CalculatedRoute route) {
        return Long.valueOf(route.getOrderedRoutes().size());
    }
}
