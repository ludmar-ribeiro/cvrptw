package com.lud.delivery.cvrptw.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.repository.ClientRepository;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public void create(Client client) {
        if(repository.existsById(client.getId()))
            throw new ObjectExistsForIdException(client);

        repository.save(client);
    }

    public void update(Client client) {
        if(!repository.existsById(client.getId()))
            throw new NotFoundException(client);

        repository.save(client);
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Client.class, id));
    }

}
