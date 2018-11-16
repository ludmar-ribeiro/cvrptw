package com.lud.delivery.cvrptw.route.constraint;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class TargetFromOtherDepotConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset) {
        if(location.isDepot())
            return true;

        Location currentDepot = getCurrentDepot(route);

        return workset
                .getDepotList(location)
                .contains(currentDepot);
    }

    private Location getCurrentDepot(CalculatedRoute route) {
        return route
                .getArc()
                .stream()
                .filter(l -> l.isDepot())
                .reduce((d1,d2) -> d2)
             .orElse(null);
    }
}
