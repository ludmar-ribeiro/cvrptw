package com.lud.delivery.cvrptw.route.domain.route;

import java.util.List;

/**
 * A synthetic route
 *
 * Any route that is not a {@link OrderedRoute}
 *
 * @author lribeiro
 *
 */
public interface SyntheticRoute extends Route{

    /**
     * Returns a {@link List} of {@link OrderedRoute} covered 
     * by this {@link SyntheticRoute}
     *
     * @return {@link List} of {@link OrderedRoute}
     */
    List<OrderedRoute> getOrderedRoutes();

    /**
     * Returns true
     */
    @Override
    default boolean isSynthetic() {
        return true;
    }
}
