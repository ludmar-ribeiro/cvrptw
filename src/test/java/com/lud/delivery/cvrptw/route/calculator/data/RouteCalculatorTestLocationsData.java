package com.lud.delivery.cvrptw.route.calculator.data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.route.calculator.data.converter.AbstractLocationConverter;

/**
 * Object that holds the test data for the tests about route calculation
 *
 * This object is also used as source of data to be 
 * injected on {@link RouteCalculatorTestData} by {@link AbstractLocationConverter}
 * 
 * @author Ludmar Ribeiro
 */
@Component
@ConfigurationProperties("test-data")
public class RouteCalculatorTestLocationsData {

    List<Client> clients;
    List<Restaurant> restaurants;

    /**
     * Returns Clients to be used during the tests
     * 
     * @return {@link List} of {@link Client}
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Sets Clients to be used during the tests
     * 
     * @param {@link List} of {@link Client}
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * Returns Restaurants to be used during the tests
     * 
     * @return {@link List} of {@link Restaurant}
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Sets Restaurants to be used during the tests
     * 
     * @param {@link List} of {@link Restaurant}
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
