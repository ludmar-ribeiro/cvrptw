package com.lud.delivery.cvrptw.route.calculator;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.depreciator.RouteDepreciator;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.composite.CompositeCalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.domain.workset.factory.RouteCalculationWorksetFactory;
import com.lud.delivery.cvrptw.route.evaluator.StopEvaluator;
import com.lud.delivery.cvrptw.route.picker.PossibleRoutesPicker;
import com.lud.delivery.cvrptw.route.picker.StartPointPicker;

@Component
public class RouteCalculator {

    @Autowired
    private RouteCalculationWorksetFactory worksetFactory;

    @Autowired
    private PreRouteCalculator preRouteCalculator;

    @Autowired
    private StartPointPicker startPointPicker;

    @Autowired
    private PossibleRoutesPicker possibleRoutesPicker;

    @Autowired
    private StopEvaluator stopEvaluator;

    @Autowired
    private RouteDepreciator routeDepreciator; 

    public List<CalculatedRoute> calculate(List<Route> routes) {
        RouteCalculationWorkset workset = worksetFactory.create(preRouteCalculator.calculate(routes));

        calculateOpenRoutes(workset);

        if(workset.getSortedClosedRoutes().isEmpty())
            return Collections.emptyList();

        return Collections.singletonList(workset.getSortedClosedRoutes().get(0));
    }

    private void calculateOpenRoutes(RouteCalculationWorkset workset) {
        CalculatedRoute route = startPointPicker.pick(workset);

        while(route != null) {
            calculatePossibleRoutes(workset, route);

            if(stopEvaluator.shouldStop(workset, route))
                return;

            route = workset.getSortedOpenRoutes().get(0);
        }
    }

    private void calculatePossibleRoutes(RouteCalculationWorkset workset, CalculatedRoute route) {
        List<CalculatedRoute> possibleRoutes = possibleRoutesPicker.pick(route, workset);

        possibleRoutes.forEach((pr) -> processPossibleRoute(workset, route, pr));

        workset.close(route);
    }

    private void processPossibleRoute(RouteCalculationWorkset workset, CalculatedRoute route, CalculatedRoute routeNewSubRoute) {
        CalculatedRoute composite = compositeRoute(route, routeNewSubRoute);

        routeDepreciator.depreciate(workset, composite);

        workset.add(composite);
    }
    private CalculatedRoute compositeRoute(CalculatedRoute route, CalculatedRoute routeNewSubRoute) {
        return new CompositeCalculatedRoute(route, routeNewSubRoute);
    }
}
