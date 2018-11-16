package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class CapacityConstraint implements RouteConstraint{

    @Value("${route.vehicle.capacity}")
    private Integer capacity;

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteWorkset workset) {
        if(candidate.getDestiny().isDepot())
            return true;

        if(rootRoute.getArc().size() < capacity)
            return true;

        return rootRoute
                .getArc()
                    .stream()
                        .skip(rootRoute.getArc().size() - capacity)
                        .filter(l -> !l.isDepot())
               .count() < capacity;
    }

}
