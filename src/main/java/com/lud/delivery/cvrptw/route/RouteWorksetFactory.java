package com.lud.delivery.cvrptw.route;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.comparator.RouteComparator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

@Component
public class RouteWorksetFactory {

    @Autowired
    private RouteComparator routeComparator;

    public RouteWorkset create(List<CalculatedRoute> routes) {
        return new RouteWorksetBuilder()
                .forRoutes(routes)
                .withComparator(routeComparator)
                .build();
    }

    private static class RouteWorksetBuilder {

        private Comparator<CalculatedRoute> comparator;

        private ObservableList<CalculatedRoute> routes;
        private ObservableList<CalculatedRoute> closedRoutes = FXCollections.observableArrayList();

        private SortedList<CalculatedRoute> sortedRoutes;
        private SortedList<CalculatedRoute> sortedClosedRoutes;

        private Map<Location, List<Location>> depotToTargetMap = new HashMap<>();
        private Map<Location, CalculatedRoute> targetToRouteMap = new HashMap<>();

        public RouteWorksetBuilder forRoutes(List<CalculatedRoute> routes) {
            this.routes = FXCollections.observableList(routes);

            return this;
        }

        public RouteWorksetBuilder withComparator(Comparator<CalculatedRoute> comparator) {
            this.comparator = comparator;

            return this;
        }

        public RouteWorkset build() {
            buildSortedLists();
            buildMaps();

            return new RouteWorkset(
                    routes,
                    closedRoutes,
                    sortedRoutes,
                    sortedClosedRoutes,
                    depotToTargetMap,
                    targetToRouteMap);
        }

        private void buildSortedLists() {
            sortedRoutes = new SortedList<>(routes, comparator);
            sortedClosedRoutes = new SortedList<>(closedRoutes, comparator);
        }

        private void buildMaps() {
            routes.forEach((r) -> putToMaps(r));
        }

        private void putToMaps(CalculatedRoute route) {
            putToDepotToTargetMap(route);
            putToTargetToDepotMap(route);
        }

        private void putToTargetToDepotMap(CalculatedRoute route) {
            targetToRouteMap.put(route.getDestiny(), route);
        }

        private void putToDepotToTargetMap(CalculatedRoute route) {
            getTargetListForDepot(route.getOrigin()).add(route.getDestiny());
        }

        private List<Location> getTargetListForDepot(Location location) {
            List<Location> list = depotToTargetMap.get(location);

            if(list == null ) {
                list = new LinkedList<>();
                depotToTargetMap.put(location, list);
            }

            return list;
        }

    }
}

