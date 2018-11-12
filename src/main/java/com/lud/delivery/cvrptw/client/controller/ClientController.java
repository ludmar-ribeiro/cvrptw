package com.lud.delivery.cvrptw.client.controller;

import static com.lud.delivery.cvrptw.common.api.config.ApiConfig.API;
import static com.lud.delivery.cvrptw.common.api.config.ApiConfig.API_VERSION;

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

@RestController
@RequestMapping(API + API_VERSION + "client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public void create(@RequestBody @Valid Client client) {
        service.create(client);
    }

    @PutMapping
    public void update(@RequestBody @Valid Client client) {
        service.update(client);
    }

    @GetMapping
    public List<Client> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public Client get(@PathVariable Integer id) {
        return service.get(id);
    }
}
