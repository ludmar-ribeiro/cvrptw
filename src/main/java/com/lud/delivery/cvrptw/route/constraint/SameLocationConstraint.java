package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class SameLocationConstraint implements RouteConstraint {

    @Override
    public boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset) {
        return !location.equals(route.getDestiny());
    }
}
