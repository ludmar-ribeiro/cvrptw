package com.lud.delivery.cvrptw.delivery.route.dto;

import java.util.List;

import com.lud.delivery.cvrptw.delivery.route.domain.DeliveryRoute;

/**
 * A DTO that contains a {@link List} of {@link DeliveryRoute}
 * to be returned as response entity body of the routes get api
 *
 * @author Ludmar Ribeiro
 *
 */
public class RoutesDTO {

    private List<DeliveryRoute> routes;

    public RoutesDTO(List<DeliveryRoute> routes) {
        this.routes = routes;
    }

    public List<DeliveryRoute> getRoutes() {
        return routes;
    }
}
