package com.lud.delivery.cvrptw.delivery.route.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lud.delivery.cvrptw.delivery.route.dto.RoutesDTO;
import com.lud.delivery.cvrptw.delivery.route.service.DeliveryRouteService;

/**
 * Controller for the routes REST api.
 *
 * @author Ludmar Ribeiro
 *
 */
@RestController
@RequestMapping("${common.api.uri}routes")
public class DeliveryRouteController {

    /**
     * Delivery route service
     */
    @Autowired
    private DeliveryRouteService service;

    /**
     * Read routes api
     *
     * Http method: GET
     *
     * @param {@link RoutesDTO} 
     */
    @GetMapping
    public RoutesDTO getAll() {
        return new RoutesDTO(service.getAll());
    }

}
