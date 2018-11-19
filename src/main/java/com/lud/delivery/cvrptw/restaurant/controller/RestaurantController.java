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

/**
 * Controller for the restaurant REST api.
 *
 * @author Ludmar Ribeiro
 *
 */
@RestController
@RequestMapping("${common.api.uri}restaurant")
public class RestaurantController{

    /**
     * Restaurant service
     */
    @Autowired
    private RestaurantService service;

    /**
     * Restaurant creation api
     *
     * Http method: POST
     *
     * @param {@link Restaurant) 
     */
    @PostMapping
    public void create(@RequestBody @Valid Restaurant restaurant) {
        service.create(restaurant);
    }

    /**
     * Restaurant update api
     *
     * Http method: PUT
     *
     * @param {@link Restaurant} 
     */
    @PutMapping
    public void update(@RequestBody @Valid Restaurant restaurant) {
        service.update(restaurant);
    }

    /**
     * Read all Restaurant api
     *
     * Http method: GET
     *
     * @return {@link List} of {@link Restaurant} 
     */
    @GetMapping
    public List<Restaurant> getAll(){
        return service.getAll();
    }

    /**
     * Read one Restaurant api
     *
     * Http method: GET
     *
     * @param id
     * @return {@link Restaurant}
     */
    @GetMapping("{id}")
    public Restaurant get(@PathVariable Integer id) {
        return service.get(id);
    }

}
