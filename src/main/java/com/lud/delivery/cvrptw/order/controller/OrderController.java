package com.lud.delivery.cvrptw.order.controller;

import static com.lud.delivery.cvrptw.common.api.config.ApiConfig.API;
import static com.lud.delivery.cvrptw.common.api.config.ApiConfig.API_VERSION;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.order.service.OrderService;

@RestController
@RequestMapping(API+API_VERSION+"order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public void create(@RequestBody Order order) throws ObjectExistsForIdException{
        service.create(order);
    }

    @PutMapping
    public void update(@RequestBody Order order) throws NotFoundException{
        service.update(order);
    }

    @GetMapping
    public List<Order> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public Order get(@PathVariable Integer id) throws NotFoundException{
        return service.get(id); 
    }

    @GetMapping(params="restaurantId")
    public List<Order> search(@RequestParam(required=true) Integer restaurantId,
            @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'") Date deliveryAfter,
            @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'") Date deliveryUntil){
        return service.search(restaurantId, deliveryAfter, deliveryUntil); 
    }
}
