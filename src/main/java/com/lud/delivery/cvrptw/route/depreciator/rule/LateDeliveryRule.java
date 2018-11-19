package com.lud.delivery.cvrptw.route.depreciator.rule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Rule that adds late delivery time value to route
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class LateDeliveryRule implements DepreciationRule{

    /**
     * Adds depreciating values to a route
     *
     * @param workset
     * @param route
     */
    @Override
    public void depreciate(RouteCalculationWorkset workset, CalculatedRoute route) {
        if(route.getDestiny().isDepot())
            return;

        LocalDateTime deliveredTime = route.getDeliveryTime();
        LocalDateTime shouldDeliverTime = getShouldDeliverTime(route);

        Double lateTime = Double.valueOf(ChronoUnit.NANOS.between(deliveredTime, shouldDeliverTime));

        if(lateTime.compareTo(Double.valueOf(0)) >= 0)
            return;

        route.setLateDeliveryTime(route.getLateDeliveryTime() + lateTime);
    }

    /**
     * Evaluates the time date a ordered route shoul be delivered
     *
     * @param route
     * @return {@link LocalDateTime}
     */
    private LocalDateTime getShouldDeliverTime(CalculatedRoute route) {
        OrderedRoute lastDelivered = route.getLastDelivered();

        return lastDelivered.getDeliveryTime();
    }
}
