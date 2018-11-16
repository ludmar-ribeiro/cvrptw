package com.lud.delivery.cvrptw.route.component;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class StopEvaluator {

    public boolean shouldStop(RouteWorkset workset, CalculatedRoute route) {
        if(route.getOrderedRoutes().size() == workset.getMap().getOrderedRoutes().size())
            return true;


        return false;
    }

}
