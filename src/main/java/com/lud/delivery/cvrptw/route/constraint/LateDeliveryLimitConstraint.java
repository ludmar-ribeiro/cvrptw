package com.lud.delivery.cvrptw.route.constraint;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class LateDeliveryLimitConstraint implements RouteConstraint {

    @Value("${route.late-delivery.max}")
    private Double maxLateDelivery;


    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset) {
        if(candidate.getDestiny().isDepot())
            return true;

        LocalDateTime deliveryTime = DateTimeUtils.ignoreSeconds(
                DateTimeUtils.addMilliseconds(
                        rootRoute.getDeliveryTime(),
                        candidate.getTravelTime()));

        LocalDateTime deliveryTimeLimit = DateTimeUtils.ignoreSeconds(
                DateTimeUtils.addMilliseconds(
                        candidate.getLastDelivered().getDeliveryTime(),
                        maxLateDelivery));

        return !deliveryTime.isAfter(deliveryTimeLimit);
    }
}
