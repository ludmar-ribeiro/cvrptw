package com.lud.delivery.cvrptw.restaurant.controller;

import static com.lud.delivery.cvrptw.common.api.config.ApiConfig.API;
import static com.lud.delivery.cvrptw.common.api.config.ApiConfig.API_VERSION;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.service.RestaurantService;

@RestController
@RequestMapping(API+API_VERSION+"restaurant")
public class RestaurantController{

    @Autowired
    private RestaurantService service;

    @PostMapping
    public void create(@RequestBody Restaurant restaurant) throws ObjectExistsForIdException {
        service.create(restaurant);
    }

    @PutMapping
    public void update(@RequestBody Restaurant restaurant) throws NotFoundException {
        service.update(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public Restaurant get(@PathVariable Integer id) throws NotFoundException{
        return service.get(id);
    }

}
