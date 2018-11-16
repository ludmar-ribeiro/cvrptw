package com.lud.delivery.cvrptw.route.component;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class PossibleRoutesPicker {

    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    public List<CalculatedRoute> pick(CalculatedRoute route, RouteWorkset workset) {
        return workset
                .getMap()
                    .get(route.getDestiny())
                        .stream()
                            .filter((r) -> possibleRouteEvaluator.isPossible(r, route, workset))
               .collect(Collectors.toList());
    }
}
