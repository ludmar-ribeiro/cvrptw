package com.lud.delivery.cvrptw.route.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculatedRouteMap {

    Map<Location, Map<Location, Set<CalculatedRoute>>> map = new HashMap<>();
    Map<Location, Set<CalculatedRoute>> routesByOriginMap = new HashMap<>();
    
    Set<CalculatedRoute> routes = new HashSet<>();
    Set<CalculatedRoute> directRoutes = new HashSet<>();

    Set<OrderedRoute> orderedRoutes = new HashSet<>();


    public Set<CalculatedRoute> get(Location origin) {
        Set<CalculatedRoute> routes = routesByOriginMap.get(origin);

        if(routes == null) {
            routes = new HashSet<>();
            routesByOriginMap.put(origin, routes);
        }

        return routes;
    }

    public Set<CalculatedRoute> get(Location origin, Location destiny) {
        return get(map, origin, destiny);
    }

    public Set<OrderedRoute> getOrderedRoutes() {
        return orderedRoutes;
    }

    public Set<CalculatedRoute> getDirectRoutes() {
        return directRoutes;
    }

    public void putAll(List<CalculatedRoute> routes) {
        routes.forEach(r -> put(r));
    }

    public void put(CalculatedRoute route) {
        get(route.getOrigin(),route.getDestiny()).add(route);
        get(route.getOrigin()).add(route);

        routes.add(route);

        if(route.getOrigin().isDepot())
            putDirectRoute(route);
    }

    private void putDirectRoute(CalculatedRoute route) {
        directRoutes.add(route);
        orderedRoutes.addAll(route.getOrderedRoutes());
    }

    private Set<CalculatedRoute> get(Map<Location, Map<Location, Set<CalculatedRoute>>> map, Location origin, Location destiny) {
        Set<CalculatedRoute> routes = get(map, origin).get(destiny);

        if(routes == null) {
            routes = new HashSet<>();
            get(map, origin).put(origin, routes);
        }

        return routes;
    }

    private Map<Location, Set<CalculatedRoute>> get(Map<Location, Map<Location, Set<CalculatedRoute>>> map, Location origin) {
        Map<Location, Set<CalculatedRoute>> routes = map.get(origin);

        if(routes == null) {
            routes = new HashMap<>();
            map.put(origin, routes);
        }

        return routes;
    }

}
