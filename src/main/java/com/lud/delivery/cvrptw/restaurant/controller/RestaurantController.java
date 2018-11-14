package com.lud.delivery.cvrptw.restaurant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.service.RestaurantService;

@RestController
@RequestMapping("${common.api.uri}restaurant")
public class RestaurantController{

    @Autowired
    private RestaurantService service;

    @PostMapping
    public void create(@RequestBody @Valid Restaurant restaurant) {
        service.create(restaurant);
    }

    @PutMapping
    public void update(@RequestBody @Valid Restaurant restaurant) {
        service.update(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public Restaurant get(@PathVariable Integer id) {
        return service.get(id);
    }

}
