package com.lud.delivery.cvrptw.route.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.comparator.RouteComparator;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;

@Component
public class StartPointPicker {

    @Autowired
    private RouteComparator comparator;

    public CalculatedRoute pick(RouteWorkset workset) {
        if(workset.getMap().getDirectRoutes().isEmpty())
            return null;

        return workset
                .getMap()
                .getDirectRoutes()
                    .stream()
                    .sorted(comparator)
                    .findFirst()
              .orElse(null);
    }

}
