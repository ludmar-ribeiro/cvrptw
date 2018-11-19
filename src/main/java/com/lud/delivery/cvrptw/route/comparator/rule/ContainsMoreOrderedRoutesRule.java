package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

/**
 * Sorting rule for calculated routes
 *
 * This rule sorts the calculated routes 
 * by descending number of ordered routes covered
 *
 * NOTE: For these sorting rules the order matters as relevance
 *       please check the @Order annotation values for each rule
 *       to make sure the relevance is correct 
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
@Order(1)
public class ContainsMoreOrderedRoutesRule implements SortingRule<CalculatedRoute> {

    /**
     * Compares two calculated routes according this rule
     *
     * @param route1
     * @param route1
     * @return int, 1, -1 or 0 
     */
    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        Long numberOfOrderedRoutes2 = getNumberOfOrderedRoutes(route2);
        Long numberOfOrderedRoutes1 = getNumberOfOrderedRoutes(route1);

        return numberOfOrderedRoutes2.compareTo(numberOfOrderedRoutes1);
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
