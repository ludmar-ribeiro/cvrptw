package com.lud.delivery.cvrptw.route.constraint;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class PickupTimeConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteCalculationWorkset workset) {
        LocalDateTime arrivalTime = DateTimeUtils
                .ignoreMilliseconds(
                        DateTimeUtils
                            .addMilliseconds(
                                        rootRoute.getDeliveryTime(),
                                        candidate.getTravelTime()));

        return !arrivalTime
                .isBefore(DateTimeUtils
                               .ignoreMilliseconds(
                                       candidate.getPickupTime()));
    }

}
