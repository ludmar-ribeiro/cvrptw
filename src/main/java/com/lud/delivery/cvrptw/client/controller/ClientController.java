package com.lud.delivery.cvrptw.client.controller;

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

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.service.ClientService;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;

@RestController
@RequestMapping(API + API_VERSION + "client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public void create(@RequestBody Client client) throws ObjectExistsForIdException{
        service.create(client);
    }

    @PutMapping
    public void update(@RequestBody Client client) throws NotFoundException{
        service.update(client);
    }

    @GetMapping
    public List<Client> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public Client get(@PathVariable Integer id) throws NotFoundException {
        return service.get(id);
    }
}
