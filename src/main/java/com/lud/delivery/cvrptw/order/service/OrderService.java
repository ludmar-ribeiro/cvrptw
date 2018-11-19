package com.lud.delivery.cvrptw.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.order.repository.OrderRepository;

/**
 * The {@link Order} Service
 *
 * @author Ludmar Ribeiro
 *
 */
@Service
public class OrderService {

    /**
     * The Order jpa repository
     */
    @Autowired
    private OrderRepository repository;

    /**
     * Saves a new {@link Order}
     *
     * @param order
     */
    public void create(Order order) {
        if (repository.existsById(order.getId()))
            throw new ObjectExistsForIdException(order);

        repository.save(order);
    }

    /**
     * Updates a existent {@link Order}
     *
     * @param order
     */
    public void update(Order order) {
        if (!repository.existsById(order.getId()))
            throw new NotFoundException(order);

        repository.save(order);
    }

    /**
     * Read all {@link Order}
     *
     * @return {@link List} of {@link Order}
     */
    public List<Order> getAll() {
        return repository.findAll(); 
    }

    /**
     * Read one {@link Order}
     *
     * @param id
     * @return {@link Order}
     */
    public Order get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Order.class, id));
    }

    /**
     * Searches {@link Order} by a given restaurantId and can filter by delivery time
     * 
     * @param restaurantId
     * @param deliveryAfter
     * @param deliveryUntil
     * @return {@link List} of {@link Order}
     */
    public List<Order> search(Integer restaurantId, LocalDateTime deliveryAfter, LocalDateTime deliveryUntil) {
        if(deliveryAfter == null && deliveryUntil == null)
            return repository.searchByRestaurant(restaurantId);

        if(deliveryUntil == null)
            return repository.searchByRestaurantAndDeliveryAfter(restaurantId, deliveryAfter);

        if(deliveryAfter == null)
            return repository.searchByRestaurantAndDeliveryUntil(restaurantId, deliveryUntil);

        return repository.searchByRestaurantAndDelivery(restaurantId, deliveryAfter, deliveryUntil);
    }

    public List<Order> getUndeliveredOrders() {
        return repository.getUndeliveredOrders();
    }
}
