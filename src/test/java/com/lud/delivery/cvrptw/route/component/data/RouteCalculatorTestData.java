package com.lud.delivery.cvrptw.route.component.data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.route.domain.SimpleCalculatedRoute;

@Component
@ConfigurationProperties("test-data")
public class RouteCalculatorTestData {

    private List<SimpleCalculatedRoute> calculatedRoutes;
    private List<Order> orderedRoutes;

    public List<SimpleCalculatedRoute> getCalculatedRoutes() {
        return calculatedRoutes;
    }

    public List<Order> getOrderedRoutes() {
        return orderedRoutes;
    }

    public void setCalculatedRoutes(List<SimpleCalculatedRoute> calculatedRoutes) {
        this.calculatedRoutes = calculatedRoutes;
    }

    public void setOrderedRoutes(List<Order> orderedRoutes) {
        this.orderedRoutes = orderedRoutes;
    }

}
