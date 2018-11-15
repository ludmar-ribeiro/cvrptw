package com.lud.delivery.cvrptw.route.constraint;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

public interface RouteConstraint {

    boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset);
}
