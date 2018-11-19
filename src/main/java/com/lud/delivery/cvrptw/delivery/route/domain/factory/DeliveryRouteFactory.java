package com.lud.delivery.cvrptw.delivery.route.domain.factory;

import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.delivery.route.domain.DeliveryRoute;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.route.domain.factory.SubRouteFactory;
import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.SubRoute;

/**
 * A factory of {@link DeliveryRoute} to be used by the 
 * route calculation engine
 *
 * @author Ludmar Ribeiro
 */
@Component
public class DeliveryRouteFactory implements SubRouteFactory{

    /**
     * Creates a {@link DeliveryRoute} for a {@link Restaurant} (depot)
     *
     * @param depot
     * @return {@link SubRoute}
     */
    @Override
    public SubRoute of(Location depot) {
        DeliveryRoute route = new DeliveryRoute();

        route.setRestaurant((Restaurant) depot);

        return route;
    }

}
