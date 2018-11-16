package com.lud.delivery.cvrptw.route.domain.factory;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.comparator.RouteComparator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.CalculatedRouteMap;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

@Component
public class RouteWorksetFactory {

    @Autowired
    private RouteComparator routeComparator;

    public RouteWorkset create(CalculatedRouteMap routes) {
        return new RouteWorksetBuilder()
                .forRoutes(routes)
                .withComparator(routeComparator)
                .build();
    }

    private static class RouteWorksetBuilder {

        private Comparator<CalculatedRoute> comparator;

        private CalculatedRouteMap map;
        private ObservableList<CalculatedRoute> routes;
        private ObservableList<CalculatedRoute> openRoutes;
        private ObservableList<CalculatedRoute> closedRoutes = FXCollections.observableArrayList();

        private SortedList<CalculatedRoute> sortedRoutes;
        private SortedList<CalculatedRoute> sortedOpenRoutes;
        private SortedList<CalculatedRoute> sortedClosedRoutes;

        public RouteWorksetBuilder forRoutes(CalculatedRouteMap routes) {
            this.map = routes;

            return this;
        }

        public RouteWorksetBuilder withComparator(Comparator<CalculatedRoute> comparator) {
            this.comparator = comparator;

            return this;
        }

        public RouteWorkset build() {
            buildLists();

            return new RouteWorkset(
                    map,
                    routes,
                    openRoutes,
                    closedRoutes,
                    sortedRoutes,
                    sortedOpenRoutes,
                    sortedClosedRoutes);
        }

        private void buildLists() {
            routes = FXCollections.observableList(map.getOrderedRoutes().stream().collect(Collectors.toList()));
            openRoutes = FXCollections.observableList(map.getOrderedRoutes().stream().collect(Collectors.toList()));
            sortedRoutes = new SortedList<>(routes, comparator);
            sortedOpenRoutes = new SortedList<>(openRoutes, comparator);
            sortedClosedRoutes = new SortedList<>(closedRoutes, comparator);
        }
    }
}

