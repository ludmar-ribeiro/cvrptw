package com.lud.delivery.cvrptw.route.constraint;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

public class DepotToDepotConstraint implements RouteConstraint{

    @Override
    public boolean isAllowed(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteWorkset workset) {
        return !(candidate.getDestiny().isDepot() && rootRoute.getDestiny().isDepot());
    }
}
