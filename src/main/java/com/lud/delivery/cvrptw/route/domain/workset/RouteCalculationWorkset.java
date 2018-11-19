package com.lud.delivery.cvrptw.route.domain.workset;

import java.util.List;
import java.util.Set;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.map.CalculatedRouteMap;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

/**
 * Route calculation Workset
 *
 * The POJO that holds all information needed for the route calculation process
 * 
 * @author Ludmar Ribeiro
 *
 */
public class RouteCalculationWorkset {

    /**
     * Map of point-to-point calculated routes
     */
    private CalculatedRouteMap map;

    /**
     * Set of all routes created during the calculation process
     */
    private Set<CalculatedRoute> routes;

    /**
     * The open routes, source for the sortedOpenRoutes
     */
    private ObservableList<CalculatedRoute> openRoutes;

    /**
     * The sorted open routes, the routes that new steps could be calculated
     */
    private SortedList<CalculatedRoute> sortedOpenRoutes;

    /**
     * The closed routes, source for the sortedClosedRoutes
     */
    private ObservableList<CalculatedRoute> closedRoutes;

    /**
     * The sorted close routes, the routes that all possible next steps was calculated
     */
    private SortedList<CalculatedRoute> sortedClosedRoutes;


    public RouteCalculationWorkset(
            CalculatedRouteMap map,
            Set<CalculatedRoute> routes,
            ObservableList<CalculatedRoute> openRoutes,
            ObservableList<CalculatedRoute> closedRoutes,
            SortedList<CalculatedRoute> sortedOpenRoutes,
            SortedList<CalculatedRoute> sortedClosedRoutes) {

        this.map = map;
        this.routes = routes;
        this.openRoutes = openRoutes;
        this.closedRoutes = closedRoutes;
        this.sortedOpenRoutes = sortedOpenRoutes;
        this.sortedClosedRoutes = sortedClosedRoutes;

    }

    public CalculatedRouteMap getMap() {
        return map;
    }

    public Set<CalculatedRoute> getRoutes() {
        return routes;
    }

    public List<CalculatedRoute> getSortedOpenRoutes() {
        return sortedOpenRoutes;
    }

    public List<CalculatedRoute> getSortedClosedRoutes() {
        return sortedClosedRoutes;
    }

    public void close(CalculatedRoute route) {
        openRoutes.remove(route);
        closedRoutes.add(route);
    }
    
    public void add(CalculatedRoute route) {
        routes.add(route);
        openRoutes.add(route);
    }
}
