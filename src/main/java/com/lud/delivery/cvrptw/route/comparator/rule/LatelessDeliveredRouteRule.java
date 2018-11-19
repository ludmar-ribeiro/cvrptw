package com.lud.delivery.cvrptw.route.comparator.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

/**
 * Sorting rule for calculated routes
 *
 * This rule sorts the calculated routes 
 * by descending late time of a route
 * 
 * NOTE: Late time is negative, so late time 0 is the greater value
 *
 * NOTE: For these sorting rules the order matters as relevance
 *       please check the @Order annotation values for each rule
 *       to make sure the relevance is correct 
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
@Order(4)
public class LatelessDeliveredRouteRule implements SortingRule<CalculatedRoute>{

    /**
     * Compares two calculated routes according this rule
     *
     * @param route1
     * @param route1
     * @return int, 1, -1 or 0 
     */
    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        return route2.getLateDeliveryTime().compareTo(route1.getLateDeliveryTime());
    }
}
