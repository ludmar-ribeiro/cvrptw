package com.lud.delivery.cvrptw.route.calculator.data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.route.domain.SimpleCalculatedRoute;

@Component
@ConfigurationProperties("test-data")
@DependsOn("routeCalculatorTestLocationsData")
public class RouteCalculatorTestData {

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

}
