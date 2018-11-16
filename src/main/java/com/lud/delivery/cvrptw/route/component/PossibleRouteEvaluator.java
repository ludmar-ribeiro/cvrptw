package com.lud.delivery.cvrptw.route.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.constraint.RouteConstraint;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class PossibleRouteEvaluator {

    @Autowired
    private List<RouteConstraint> constraints;

    public boolean isPossible(CalculatedRoute candidate, CalculatedRoute rootRoute, RouteWorkset workset) {

        return constraints
                .stream()
                    .map(c -> c.isAllowed(candidate, rootRoute, workset))
                    .reduce((b1, b2) -> b1 && b2)
            .orElse(true); 
    }
}
