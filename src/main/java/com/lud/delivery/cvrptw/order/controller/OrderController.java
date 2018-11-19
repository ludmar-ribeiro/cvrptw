package com.lud.delivery.cvrptw.order.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

/**
 * Controller for the order REST api.
 *
 * @author Ludmar Ribeiro
 *
 */
@RestController
@RequestMapping("${common.api.uri}order")
public class OrderController {

    /**
     * the order service
     */
    @Autowired
    private OrderService service;

    /**
     * Order creation api
     *
     * Http method: POST
     *
     * @param {@link Order} 
     */
    @PostMapping
    public void create(@RequestBody @Valid Order order) throws ObjectExistsForIdException{
        service.create(order);
    }

    /**
     * Order update api
     *
     * Http method: PUT
     *
     * @param {@link Order} 
     */
    @PutMapping
    public void update(@RequestBody @Valid Order order) throws NotFoundException{
        service.update(order);
    }

    /**
     * Read all Order api
     *
     * Http method: GET
     *
     * @return {@link List} of {@link Order} 
     */
    @GetMapping
    public List<Order> getAll(){
        return service.getAll();
    }

    /**
     * Read one Order api
     *
     * Http method: GET
     *
     * @param id
     * @return {@link Order}
     */
    @GetMapping("{id}")
    public Order get(@PathVariable Integer id) throws NotFoundException{
        return service.get(id); 
    }

    /**
     * Search Order api
     *
     * Searches order by the given restaurantId and can filter by delivery time
     *
     * Http method: GET
     *
     * @param restaurantId
     * @param deliveryAfter
     * @param deliveryUntil
     * @return {@link List} of {@link Order} 
     */
    @GetMapping(params="restaurantId")
    public List<Order> search(@RequestParam(required=true) Integer restaurantId,
            @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'") LocalDateTime deliveryAfter,
            @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'") LocalDateTime deliveryUntil){
        return service.search(restaurantId, deliveryAfter, deliveryUntil); 
    }
}
