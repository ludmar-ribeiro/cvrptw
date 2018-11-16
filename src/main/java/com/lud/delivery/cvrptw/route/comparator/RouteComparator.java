package com.lud.delivery.cvrptw.route.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;

@Component
public class RouteComparator implements Comparator<CalculatedRoute> {

    @Override
    public int compare(CalculatedRoute route1, CalculatedRoute route2) {
        Long numberOfOrderedRoutes2 = compareNumberOfOrderedRoutes(route2);
        Long numberOfOrderedRoutes1 = compareNumberOfOrderedRoutes(route1);

        int result = numberOfOrderedRoutes2.compareTo(numberOfOrderedRoutes1);

        if(result != 0)
            return result;

        if(numberOfOrderedRoutes1.equals(Long.valueOf(1)))
            result = DateTimeUtils.ignoreMilliseconds(route1.getPickupTime()).compareTo(DateTimeUtils.ignoreMilliseconds(route2.getPickupTime())); 

        if(result == 0)
            result = route1.getTravelTime().compareTo(route2.getTravelTime());

        return result;
    }

    private Long compareNumberOfOrderedRoutes(CalculatedRoute route) {
        return Long.valueOf(route.getOrderedRoutes().size());
    }

}
