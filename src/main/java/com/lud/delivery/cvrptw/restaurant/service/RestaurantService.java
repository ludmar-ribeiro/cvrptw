package com.lud.delivery.cvrptw.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.repository.RestaurantRepository;

/**
 * Restaurant service
 *
 * @author Ludmar Ribeiro
 *
 */
@Service
public class RestaurantService{

    /**
     * Restaurant's JPA repository
     */
    @Autowired
    private RestaurantRepository repository;

    /**
     * Saves a new Restaurant
     *
     * @param {@link Restaurant}
     */
    public void create(Restaurant restaurant) {
        if(repository.existsById(restaurant.getId()))
            throw new ObjectExistsForIdException(restaurant);

        repository.save(restaurant);
    }

    /**
     * Updates a new Restaurant
     *
     * @param {@link Restaurant}
     */
    public void update(Restaurant restaurant) {
        if(!repository.existsById(restaurant.getId()))
            throw new NotFoundException(restaurant);

        repository.save(restaurant);
    }

    /**
     * Read all Restaurants
     *
     * @return {@link List} of {@link Restaurant}
     */
    public Restaurant get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Restaurant.class, id));
    }

    /**
     * Read one {@link Restaurant}
     *
     * @param id
     * @return {@link Restaurant}
     */
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
