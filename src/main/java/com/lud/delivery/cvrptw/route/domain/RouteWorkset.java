package com.lud.delivery.cvrptw.route.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class RouteWorkset {

    private ObservableList<CalculatedRoute> routes;
    private SortedList<CalculatedRoute> sortedRoutes;

    private ObservableList<CalculatedRoute> closedRoutes;
    private SortedList<CalculatedRoute> sortedClosedRoutes;

    private Map<Location, List<Location>> depotToTargetMap;
    private Map<Location, CalculatedRoute> targetToRouteMap;

    public RouteWorkset(
            ObservableList<CalculatedRoute> routes,
            ObservableList<CalculatedRoute> closedRoutes,
            SortedList<CalculatedRoute> sortedRoutes,
            SortedList<CalculatedRoute> sortedClosedRoutes,
            Map<Location, List<Location>> depotToTargetMap,
            Map<Location, CalculatedRoute> targetToRouteMap) {

        this.routes = routes;
        this.closedRoutes = closedRoutes;
        this.sortedRoutes = sortedRoutes;
        this.sortedClosedRoutes = sortedClosedRoutes;
        this.depotToTargetMap = depotToTargetMap;
        this.targetToRouteMap = targetToRouteMap;

    }

    public List<CalculatedRoute> getSortedRoutes() {
        return sortedRoutes;
    }

    public List<CalculatedRoute> getSortedClosedRoutes() {
        return sortedClosedRoutes;
    }

    public Map<Location, List<Location>> getDepotToTargetMap() {
        return depotToTargetMap;
    }
    
    
    public Map<Location, CalculatedRoute> getTargetToRouteMap() {
        return targetToRouteMap;
    }

    public Location getDepot(Location location) {
        if(location.isDepot())
            return location;

        return targetToRouteMap.get(location).getOrigin();
    }

    public List<Location> getTargetsForDepot(Location location) {
        if(!location.isDepot())
            return Collections.emptyList();

        return depotToTargetMap.getOrDefault(location, Collections.emptyList());
    }

    public void close(CalculatedRoute route) {
        routes.remove(route);
        closedRoutes.add(route);
    }
    
    public void add(CalculatedRoute route) {
        routes.add(route);
    }
}
