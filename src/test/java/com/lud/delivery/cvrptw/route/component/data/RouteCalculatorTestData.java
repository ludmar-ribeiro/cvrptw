package com.lud.delivery.cvrptw.route.component.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.route.domain.SimpleCalculatedRoute;

@Component
@ConfigurationProperties("test-data")
public class RouteCalculatorTestData {

    @Autowired
    private RouteCalculatorTestLocationsData locationsData;

    private List<SimpleCalculatedRoute> calculatedRoutes;
    private List<Order> orderedRoutes;

    public List<Order> getOrderedRoutes() {
        return orderedRoutes;
    }

    public void setOrderedRoutes(List<Order> orderedRoutes) {
        this.orderedRoutes = orderedRoutes;
    }

    public List<SimpleCalculatedRoute> getCalculatedRoutes() {
        return calculatedRoutes;
    }
    
    public void setCalculatedRoutes(List<SimpleCalculatedRoute> calculatedRoutes) {
        this.calculatedRoutes = calculatedRoutes;
    }

    public List<Restaurant> getRestaurants() {
        return locationsData.getRestaurants();
    }

    public List<Client> getClients() {
        return locationsData.getClients();
    }
}
