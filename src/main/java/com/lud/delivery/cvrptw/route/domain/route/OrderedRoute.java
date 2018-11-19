package com.lud.delivery.cvrptw.route.domain.route;

/**
 * Ordered Route
 *
 * The route of a supply delivery
 *
 * @author Ludmar Ribeiro
 *
 */
public interface OrderedRoute extends Route {

    /**
     * Returns false
     */
    @Override
    default boolean isSynthetic() {
        return false;
    }
}
