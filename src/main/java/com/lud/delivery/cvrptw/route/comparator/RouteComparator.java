package com.lud.delivery.cvrptw.route.comparator;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.comparator.RuledComparator;
import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;

/**
 * Comparator for calculated routes that uses {@link SortingRule}
 *
 * This component is used during the whole calculation process,
 * this component determines the better route for all calculation
 * process steps
 *
 * NOTE: For these sorting rules the order matters as relevance
 *       please check the @Order annotation values for each rule
 *       to make sure the relevance is correct 
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class RouteComparator extends RuledComparator<CalculatedRoute> {
}
