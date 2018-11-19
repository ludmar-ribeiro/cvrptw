package com.lud.delivery.cvrptw.client.controller;

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

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.service.ClientService;

/**
 * Controller for the client REST api.
 *
 * @author Ludmar Ribeiro
 *
 */
@RestController
@RequestMapping("${common.api.uri}client")
public class ClientController {

    /**
     * Client service
     */
    @Autowired
    private ClientService service;

    /**
     * Client creation api
     *
     * Http method: POST
     *
     * @param {@link Client} 
     */
    @PostMapping
    public void create(@RequestBody @Valid Client client) {
        service.create(client);
    }

    /**
     * Client update api
     *
     * Http method: PUT
     *
     * @param {@link Client} 
     */
    @PutMapping
    public void update(@RequestBody @Valid Client client) {
        service.update(client);
    }

    /**
     * Read all Client api
     *
     * Http method: GET
     *
     * @return {@link List} of {@link Client} 
     */
    @GetMapping
    public List<Client> getAll(){
        return service.getAll();
    }

    /**
     * Read one Client api
     *
     * Http method: GET
     *
     * @param id
     * @return {@link Client}
     */
    @GetMapping("{id}")
    public Client get(@PathVariable Integer id) {
        return service.get(id);
    }
}
