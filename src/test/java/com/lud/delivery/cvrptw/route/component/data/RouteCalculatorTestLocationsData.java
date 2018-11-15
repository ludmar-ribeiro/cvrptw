package com.lud.delivery.cvrptw.route.component.data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;

@Component
@ConfigurationProperties("test-data")
public class RouteCalculatorTestLocationsData {

    List<Client> clients;
    List<Restaurant> restaurants;

    public List<Client> getClients() {
        return clients;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
