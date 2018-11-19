package com.lud.delivery.cvrptw.route.domain.workset.factory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.comparator.RouteComparator;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.map.CalculatedRouteMap;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

@Component
public class RouteCalculationWorksetFactory {

    @Autowired
    private RouteComparator routeComparator;

    public RouteCalculationWorkset create(CalculatedRouteMap routes) {
        return new RouteWorksetBuilder()
                .forRoutes(routes)
                .withComparator(routeComparator)
                .build();
    }

    private static class RouteWorksetBuilder {

        private Comparator<CalculatedRoute> comparator;

        private CalculatedRouteMap map;
        private Set<CalculatedRoute> routes = new HashSet<>();
        private ObservableList<CalculatedRoute> openRoutes = FXCollections.observableArrayList();
        private ObservableList<CalculatedRoute> closedRoutes = FXCollections.observableArrayList();

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

        public RouteCalculationWorkset build() {
            buildLists();

            return new RouteCalculationWorkset(
                    map,
                    routes,
                    openRoutes,
                    closedRoutes,
                    sortedOpenRoutes,
                    sortedClosedRoutes);
        }

        private void buildLists() {
            sortedOpenRoutes = new SortedList<>(openRoutes, comparator);
            sortedClosedRoutes = new SortedList<>(closedRoutes, comparator);
        }
    }
}

