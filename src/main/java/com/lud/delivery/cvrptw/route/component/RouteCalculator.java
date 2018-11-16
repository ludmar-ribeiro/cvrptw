package com.lud.delivery.cvrptw.route.component;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.factory.RouteWorksetFactory;

@Component
public class RouteCalculator {

    @Autowired
    private RouteWorksetFactory worksetFactory;

    @Autowired
    private PreRouteCalculator preRouteCalculator;

    @Autowired
    private StartPointPicker startPointPicker;

    @Autowired
    private PossibleRoutesPicker possibleRoutesPicker;

    @Autowired
    private StopEvaluator stopEvaluator; 

    public List<CalculatedRoute> calculate(List<Route> routes) {
        RouteWorkset workset = worksetFactory.create(preRouteCalculator.calculate(routes));

        calculateOpenRoutes(workset);

        if(workset.getSortedClosedRoutes().isEmpty())
            return Collections.emptyList();

        return Collections.singletonList(workset.getSortedClosedRoutes().get(0));
    }

    private void calculateOpenRoutes(RouteWorkset workset) {
        CalculatedRoute route = startPointPicker.pick(workset);

        while(route != null) {
            calculatePossibleRoutes(workset, route);

            if(workset.getSortedOpenRoutes().isEmpty())
                return;

            if(stopEvaluator.shouldStop(workset, route))
                return;

            route = workset.getSortedOpenRoutes().get(0);
        }
    }

    private void calculatePossibleRoutes(RouteWorkset workset, CalculatedRoute route) {
        List<CalculatedRoute> possibleRoutes = possibleRoutesPicker.pick(route, workset);

        possibleRoutes.forEach((pr) -> workset.add(compositeRoute(route, pr)));

        workset.close(route);
    }

    private CalculatedRoute compositeRoute(CalculatedRoute route, CalculatedRoute routeNewSubRoute) {
        return new CompositeCalculatedRoute(route, routeNewSubRoute);
    }
}
