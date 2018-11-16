package com.lud.delivery.cvrptw.route.component;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class StartPointPicker {

    public CalculatedRoute pick(RouteWorkset fullRoute) {
        if(fullRoute.getSortedOpenRoutes().isEmpty())
            return null;

        return fullRoute.getSortedOpenRoutes().get(0);
    }

}
