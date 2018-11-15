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

        Location depot = route.getDestiny().isDepot() ? route.getDestiny() : workset.getDepot(route.getDestiny());

        return depot.equals(workset.getDepot(location));
    }
}
