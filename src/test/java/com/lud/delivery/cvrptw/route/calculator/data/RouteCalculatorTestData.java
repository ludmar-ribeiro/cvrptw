package com.lud.delivery.cvrptw.route.calculator.data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.route.domain.SimpleCalculatedRoute;

/**
 * Object that holds the test data for the tests about route calculation
 *
 * @author Ludmar Ribeiro
 */
@Component
@ConfigurationProperties("test-data")
@DependsOn("routeCalculatorTestLocationsData")
public class RouteCalculatorTestData {

    private List<SimpleCalculatedRoute> calculatedRoutes;
    private List<Order> orderedRoutes;

    /**
     * Returns data of orders to be used during the tests
     * 
     * @return {@link List} of {@link Order}
     */
    public List<Order> getOrderedRoutes() {
        return orderedRoutes;
    }

    /**
     * Returns data of orders to be used during the tests
     *
     * @param {@link List} of {@link Order}
     */
    public void setOrderedRoutes(List<Order> orderedRoutes) {
        this.orderedRoutes = orderedRoutes;
    }

    /**
     * Returns data of the calculated routes expected during the tests
     *
     * @return {@link List} of {@link SimpleCalculatedRoute}
     */
    public List<SimpleCalculatedRoute> getCalculatedRoutes() {
        return calculatedRoutes;
    }

    /**
     * Sets data of the calculated routes expected during the tests
     *
     * @param {@link List} of {@link SimpleCalculatedRoute}
     */
    public void setCalculatedRoutes(List<SimpleCalculatedRoute> calculatedRoutes) {
        this.calculatedRoutes = calculatedRoutes;
    }

}
