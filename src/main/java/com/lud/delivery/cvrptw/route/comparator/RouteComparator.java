package com.lud.delivery.cvrptw.route.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;

@Component
public class RouteComparator implements Comparator<CalculatedRoute> {

    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        return route1.getTravelTime().compareTo(route2.getTravelTime());
    }

}
