package com.lud.delivery.cvrptw.route.domain;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class RouteWorkset {

    private CalculatedRouteMap map;

    private ObservableList<CalculatedRoute> routes;
    private SortedList<CalculatedRoute> sortedRoutes;

    private ObservableList<CalculatedRoute> openRoutes;
    private SortedList<CalculatedRoute> sortedOpenRoutes;

    private ObservableList<CalculatedRoute> closedRoutes;
    private SortedList<CalculatedRoute> sortedClosedRoutes;


    public RouteWorkset(
            CalculatedRouteMap map,
            ObservableList<CalculatedRoute> routes,
            ObservableList<CalculatedRoute> openRoutes,
            ObservableList<CalculatedRoute> closedRoutes,
            SortedList<CalculatedRoute> sortedRoutes,
            SortedList<CalculatedRoute> sortedOpenRoutes,
            SortedList<CalculatedRoute> sortedClosedRoutes) {

        this.map = map;
        this.routes = routes;
        this.openRoutes = openRoutes;
        this.closedRoutes = closedRoutes;
        this.sortedRoutes = sortedRoutes;
        this.sortedOpenRoutes = sortedOpenRoutes;
        this.sortedClosedRoutes = sortedClosedRoutes;

    }

    public CalculatedRouteMap getMap() {
        return map;
    }

    public SortedList<CalculatedRoute> getSortedRoutes() {
        return sortedRoutes;
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
