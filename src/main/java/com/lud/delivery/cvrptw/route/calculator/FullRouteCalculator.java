package com.lud.delivery.cvrptw.route.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.depreciator.RouteDepreciator;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;
import com.lud.delivery.cvrptw.route.domain.route.fullRoute.FullCalculatedRouteFactory;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.domain.workset.factory.RouteCalculationWorksetFactory;
import com.lud.delivery.cvrptw.route.evaluator.StopEvaluator;
import com.lud.delivery.cvrptw.route.picker.PossibleRoutesPicker;
import com.lud.delivery.cvrptw.route.picker.StartPointPicker;

/**
 * Main component of the route calculation engine
 *
 * This component calculates a optimal route for a list of ordered routes 
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class FullRouteCalculator {

    /**
     * Route calculation workset factory
     */
    @Autowired
    private RouteCalculationWorksetFactory worksetFactory;

    /**
     * Route pre-calculator
     */
    @Autowired
    private PreRouteCalculator preRouteCalculator;

    /**
     * Start point picker
     */
    @Autowired
    private StartPointPicker startPointPicker;

    /**
     * Possible routes picker
     */
    @Autowired
    private PossibleRoutesPicker possibleRoutesPicker;

    /**
     * Stop calculation evaluator 
     */
    @Autowired
    private StopEvaluator stopEvaluator;

    /**
     * Route depreciator
     */
    @Autowired
    private RouteDepreciator routeDepreciator;

    /**
     * Full calculated route factory
     */
    @Autowired
    private FullCalculatedRouteFactory fullCalculatedRouteFactory; 

    /**
     * Calculates the full route 
     *
     * @param routes
     * @return {@link CalculatedRoute} or null
     */
    public CalculatedRoute calculate(List<Route> routes) {
        //Pre-calculates the point-to-point routes 
        //and creates a workset for the calculation process
        RouteCalculationWorkset workset = worksetFactory.create(preRouteCalculator.calculate(routes));

        //Calculates the open possible routes
        calculateOpenRoutes(workset);

        //If no route is possible return null
        if(workset.getSortedClosedRoutes().isEmpty())
            return null;

        return workset.getSortedClosedRoutes().get(0);
    }

    /**
     * Calculates the open possible routes
     *
     * @param workset
     */
    private void calculateOpenRoutes(RouteCalculationWorkset workset) {
        //Pick a start point
        CalculatedRoute route = startPointPicker.pick(workset);

        //Calculates the possible open routes
        while(route != null) {
            //Calculates the possible next steps for a given route
            calculatePossibleRoutes(workset, route);

            //Evaluates if the calculation is done 
            if(stopEvaluator.shouldStop(workset, route))
                return;

            route = workset.getSortedOpenRoutes().get(0);
        }
    }

    /**
     * Calculates the possible next steps for a given route
     *
     * @param workset
     * @param route
     */
    private void calculatePossibleRoutes(RouteCalculationWorkset workset, CalculatedRoute route) {
        //Pick possible next steps
        List<CalculatedRoute> possibleRoutes = possibleRoutesPicker.pick(route, workset);

        //Creates new routes adding the next steps
        possibleRoutes.forEach((pr) -> processPossibleRoute(workset, route, pr));

        //Closes the route processed 
        workset.close(route);
    }

    /**
     * Creates a new route adding a next step
     *
     * @param workset
     * @param route
     * @param routeNewSubRoute
     */
    private void processPossibleRoute(RouteCalculationWorkset workset, CalculatedRoute route, CalculatedRoute routeNewSubRoute) {
        //Creates a new route adding a next step
        CalculatedRoute composite = compositeRoute(route, routeNewSubRoute);

        //Depreciate the new route according to the depreciation rules
        routeDepreciator.depreciate(workset, composite);

        //Add the new route to the workset
        workset.add(composite);
    }

    /**
     * Creates a new route adding a next step
     *
     * @param route
     * @param routeNewSubRoute
     * @return {@link CalculatedRoute}
     */
    private CalculatedRoute compositeRoute(CalculatedRoute route, CalculatedRoute routeNewSubRoute) {
        return fullCalculatedRouteFactory.of(route, routeNewSubRoute);
    }
}
