package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class CapacityConstraint implements RouteConstraint{

    @Value("${route.vehicle.capacity}")
    private Integer capacity;

    @Override
    public boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset) {
        if(location.isDepot())
            return true;

        if(route.getArc().size() < capacity)
            return true;

        return route
                .getArc()
                    .stream()
                        .skip(route.getArc().size() - capacity)
                        .filter(l -> !l.isDepot())
               .count() < capacity;
    }

}
