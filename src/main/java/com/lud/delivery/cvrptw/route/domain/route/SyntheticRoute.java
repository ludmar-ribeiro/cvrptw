package com.lud.delivery.cvrptw.route.domain.route;

import java.util.List;

public interface SyntheticRoute extends Route{

    List<OrderedRoute> getOrderedRoutes();

    @Override
    default boolean isSynthetic() {
        return true;
    }
}
