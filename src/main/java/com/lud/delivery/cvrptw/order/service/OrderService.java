package com.lud.delivery.cvrptw.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.order.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public void create(Order order) throws ObjectExistsForIdException {
        if (repository.existsById(order.getId()))
            throw new ObjectExistsForIdException(order);

        repository.save(order);
    }

    public void update(Order order) throws NotFoundException {
        if (!repository.existsById(order.getId()))
            throw new NotFoundException(order);

        repository.save(order);
    }

    public List<Order> getAll() {
        return repository.findAll(); 
    }

    public Order get(Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Order.class, id));
    }

    public List<Order> search(Integer restaurantId, LocalDateTime deliveryAfter, LocalDateTime deliveryUntil) {
        if(deliveryAfter == null && deliveryUntil == null)
            return repository.searchByRestaurant(restaurantId);

        if(deliveryUntil == null)
            return repository.searchByRestaurantAndDeliveryAfter(restaurantId, deliveryAfter);

        if(deliveryAfter == null)
            return repository.searchByRestaurantAndDeliveryUntil(restaurantId, deliveryUntil);

        return repository.searchByRestaurantAndDelivery(restaurantId, deliveryAfter, deliveryUntil);
    }
}
