package com.lud.delivery.cvrptw.route.constraint;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.component.SubRouteCalculator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;

@Component
public class ExistentRouteConstraint implements RouteConstraint{

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    @Override
    public boolean isAllowed(Location location, CalculatedRoute route, RouteWorkset workset) {

        List<CalculatedRoute> simulations = simulateRoute(workset, route, location);

        return !Stream.concat(
                    workset.getSortedRoutes().stream(),
                    workset.getSortedClosedRoutes().stream())
                .collect(Collectors.toList())
                .containsAll(simulations);
    }

    private List<CalculatedRoute> simulateRoute(RouteWorkset workset, CalculatedRoute route, Location destiny) {

        return subRouteCalculator
                .calculate(workset, route.getDestiny(), destiny)
                    .stream()
                    .map(r -> new CompositeCalculatedRoute(route, r))
                .collect(Collectors.toList());
    }
}
