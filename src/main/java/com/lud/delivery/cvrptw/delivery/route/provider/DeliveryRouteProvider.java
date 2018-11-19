package com.lud.delivery.cvrptw.delivery.route.provider;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.delivery.route.domain.DeliveryRoute;
import com.lud.delivery.cvrptw.delivery.route.repository.DeliveryRouteRepository;
import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.order.service.OrderService;
import com.lud.delivery.cvrptw.route.calculator.FullRouteCalculator;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.SubRoute;

/**
 * {@link DeliveryRoute} provider
 *
 * Calls the route calculation engine to create a route
 * for a {@link List} of {@link Order} and return the
 * generated {@link List} of {@link DeliveryRoute}
 * 
 *
 * @author Ludmar Ribeiro
 *
 */
@Service
public class DeliveryRouteProvider {

    /**
     * Order service
     */
    @Autowired
    private OrderService orderService;

    /**
     * Route calculator component
     */
    @Autowired
    private FullRouteCalculator calculator;

    /**
     * {@link DeliveryRoute} jpa repository
     */
    @Autowired
    private DeliveryRouteRepository repository;

    /**
     * Calls the route calculation engine for a {@link List} of {@link Order}
     * retrieved and return a {@link List} of {@link DeliveryRoute} generated 
     *
     * @return {@link List} of {@link DeliveryRoute}
     */
    public List<DeliveryRoute> getAll() {
        List<Order> orders = orderService.getUndeliveredOrders();

        CalculatedRoute fullRoute = calculator.calculate(orders.stream().collect(Collectors.toList()));

        if(fullRoute == null)
            return Collections.emptyList();

        return fullRoute
                .getSubRoutes()
                    .stream()
                        .map(r -> processRoute(r))
              .collect(Collectors.toList());
    }

    /**
     * Saves the {@link DeliveryRoute}
     *
     * @param route
     * @return {@link DeliveryRoute}
     */
    private DeliveryRoute processRoute(SubRoute route) {
        DeliveryRoute deliveryRoute = (DeliveryRoute) route;

        repository.save(deliveryRoute);

        return deliveryRoute;
    }
}
