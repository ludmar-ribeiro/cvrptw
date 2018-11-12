package com.lud.delivery.cvrptw.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantService{

    @Autowired
    private RestaurantRepository repository;

    public void create(Restaurant restaurant) throws ObjectExistsForIdException {
        if(repository.existsById(restaurant.getId()))
            throw new ObjectExistsForIdException(restaurant);

        repository.save(restaurant);
    }

    public void update(Restaurant restaurant) throws NotFoundException {
        if(!repository.existsById(restaurant.getId()))
            throw new NotFoundException(restaurant);

        repository.save(restaurant);
    }

    public Restaurant get(Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Restaurant.class, id));
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
