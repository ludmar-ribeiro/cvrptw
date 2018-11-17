package com.lud.delivery.cvrptw.route.depreciator.rule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class LateDeliveryRule implements DepreciationRule{

    @Override
    public void depreciate(RouteCalculationWorkset workset, CalculatedRoute route) {
        if(route.getDestiny().isDepot())
            return;

        LocalDateTime deliveredTime = DateTimeUtils.ignoreMilliseconds(route.getDeliveryTime());
        LocalDateTime shouldDeliverTime = getShouldDeliverTime(route);

        Double lateTime = Double.valueOf(ChronoUnit.NANOS.between(deliveredTime, shouldDeliverTime));

        if(lateTime.compareTo(Double.valueOf(0)) >= 0)
            return;

        route.setLateDeliveryTime(route.getLateDeliveryTime() + lateTime);
    }

    private LocalDateTime getShouldDeliverTime(CalculatedRoute route) {
        OrderedRoute lastDelivered = route.getLastDelivered();

        return lastDelivered.getDeliveryTime();
    }
}
