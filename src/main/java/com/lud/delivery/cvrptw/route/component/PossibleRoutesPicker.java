package com.lud.delivery.cvrptw.route.component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class PossibleRoutesPicker {

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    public List<CalculatedRoute> pick(CalculatedRoute route, RouteWorkset workset) {
        List<Location> possibleTargets = pickPossibleTargets(route, workset);

        return possibleTargets
                .stream()
                .flatMap(l -> subRouteCalculator.calculate(workset, route.getDestiny(), l).stream())
            .collect(Collectors.toList());
    }

    private List<Location> pickPossibleTargets(CalculatedRoute route, RouteWorkset workset) {
        return Stream.concat(
                    workset.getTargetToRouteMap().keySet().stream(),
                    workset.getDepotToTargetMap().keySet().stream())
                .filter((l) -> possibleRouteEvaluator.isPossible(l, route, workset))
            .collect(Collectors.toList());
    }
}
