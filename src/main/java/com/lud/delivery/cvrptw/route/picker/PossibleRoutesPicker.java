package com.lud.delivery.cvrptw.route.picker;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.comparator.RouteComparator;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.evaluator.PossibleRouteEvaluator;

@Component
public class PossibleRoutesPicker {

    @Autowired
    private PossibleRouteEvaluator possibleRouteEvaluator;

    @Autowired
    private RouteComparator comparator; 

    public List<CalculatedRoute> pick(CalculatedRoute route, RouteCalculationWorkset workset) {
        return workset
                .getMap()
                    .get(route.getDestiny())
                        .stream()
                            .filter((r) -> possibleRouteEvaluator.isPossible(r, route, workset))
                            .sorted(comparator)
               .collect(Collectors.toList());
    }
}
