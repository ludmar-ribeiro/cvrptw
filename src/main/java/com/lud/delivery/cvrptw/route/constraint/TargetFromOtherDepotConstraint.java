package com.lud.delivery.cvrptw.route.constraint;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

public class TargetFromOtherDepotConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteWorkset workset) {
        if(candidate.getDestiny().isDepot())
            return true;

        Location currentDepot = rootRoute.getCurrentDepot();

        if(candidate.getCurrentDepot() != null)
            return currentDepot.equals(candidate.getCurrentDepot());

        return !workset
                    .getMap()
                    .getOrderedByDestiny(candidate.getDestiny(), currentDepot)
               .isEmpty();
    }
}
