package com.lud.delivery.cvrptw.route.domain;

public interface OrderedRoute extends Route {

    @Override
    default boolean isSynthetic() {
        return false;
    }
}
