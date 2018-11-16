package com.lud.delivery.cvrptw.route.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculatedRouteMap {

    Map<Location, Map<Location, Set<CalculatedRoute>>> map = new HashMap<>();
    Map<Location, Set<CalculatedRoute>> routesFromOrigin = new HashMap<>();
    Map<Location, Map<Location, Set<CalculatedRoute>>> orderedRoutesByOrigin = new HashMap<>();
    Map<Location, Map<Location, Set<CalculatedRoute>>> orderedRoutesByDestiny = new HashMap<>();

    Set<CalculatedRoute> routes = new HashSet<>();
    Set<CalculatedRoute> orderedRoutes = new HashSet<>();
    
    public Set<CalculatedRoute> get() {
        return routes;
    }

    public Set<CalculatedRoute> getOrderedRoutes() {
        return orderedRoutes;
    }

    public void putAll(List<CalculatedRoute> routes) {
        routes.forEach(r -> put(r));
    }

    public void put(CalculatedRoute route) {
        get(route.getOrigin(),route.getDestiny()).add(route);
        get(route.getOrigin()).add(route);

        routes.add(route);

        if(!route.isSynthetic())
            putOrderedRoute(route);
    }

    public Set<CalculatedRoute> get(Location origin) {
        Set<CalculatedRoute> routes = routesFromOrigin.get(origin);

        if(routes == null) {
            routes = new HashSet<>();
            routesFromOrigin.put(origin, routes);
        }

        return routes;
    }

    public Set<CalculatedRoute> get(Location origin, Location destiny) {
        return get(map, origin, destiny);
    }

    public Set<CalculatedRoute> getOrderedByDestiny(Location destiny, Location origin) {
        return get(orderedRoutesByDestiny, destiny, origin);
    }

    public Set<CalculatedRoute> getOrdered(Location origin, Location destiny) {
        return get(orderedRoutesByOrigin, origin, destiny);
    }

    private void putOrderedRoute(CalculatedRoute route) {
        getOrdered(route.getOrigin(), route.getDestiny()).add(route);
        getOrderedByDestiny(route.getDestiny(), route.getOrigin()).add(route);
        orderedRoutes.add(route);
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
