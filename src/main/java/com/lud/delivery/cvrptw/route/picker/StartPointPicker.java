package com.lud.delivery.cvrptw.route.picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.comparator.RouteComparator;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;

/**
 * Start point picker
 * 
 * Component that picks the better ordered route to start a route
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class StartPointPicker {

    /**
     * Route comparator
     */
    @Autowired
    private RouteComparator comparator;

    /**
     * Picks the better ordered route to start a route
     *
     * @param workset
     * @return {@link CalculatedRoute}
     */
    public CalculatedRoute pick(RouteCalculationWorkset workset) {
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
