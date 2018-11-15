package com.lud.delivery.cvrptw.route.component;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.RouteWorksetFactory;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.composite.CompositeCalculatedRoute;

@Component
public class RouteCalculator {

    @Autowired
    private RouteWorksetFactory worksetFactory;

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    @Autowired
    private StartPointPicker startPointPicker;

    @Autowired
    private PossibleRoutesPicker possibleRoutesPicker; 

    public List<CalculatedRoute> calculate(List<Route> routes) {
        RouteWorkset workset = worksetFactory.create(subRouteCalculator.calculate(routes));

        calculateOpenRoutes(workset);

        if(workset.getSortedClosedRoutes().isEmpty())
            return Collections.emptyList();

        return Collections.singletonList(workset.getSortedClosedRoutes().get(0));
    }

    private void calculateOpenRoutes(RouteWorkset workset) {
        CalculatedRoute route = startPointPicker.pick(workset);

        while(route != null) {
            calculatePossibleRoutes(workset, route);

            if(workset.getSortedRoutes().isEmpty())
                return;

            route = workset.getSortedRoutes().get(0);
        }
    }

    private void calculatePossibleRoutes(RouteWorkset workset, CalculatedRoute route) {
        List<CalculatedRoute> possibleRoutes = possibleRoutesPicker.pick(route, workset);

        if(possibleRoutes.isEmpty()) {
            workset.close(route);
            return;
        }

        possibleRoutes.forEach((pr) -> workset.add(compositeRoute(route, pr)));
    }

    private CalculatedRoute compositeRoute(CalculatedRoute route, CalculatedRoute routeNewSubRoute) {
        return new CompositeCalculatedRoute(route, routeNewSubRoute);
    }
}