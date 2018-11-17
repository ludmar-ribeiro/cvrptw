package com.lud.delivery.cvrptw.route.domain.route;

public interface OrderedRoute extends Route {

    @Override
    default boolean isSynthetic() {
        return false;
    }
}
