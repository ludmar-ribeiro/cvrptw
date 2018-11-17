package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

@Component
public class TargetVisitedOnceConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute routeRoute, RouteCalculationWorkset workset) {
        return candidate.getDestiny().isDepot() 
                || !routeRoute.getArc().contains(candidate.getDestiny());
    }
}
