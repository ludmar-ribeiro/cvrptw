package com.lud.delivery.cvrptw.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.repository.ClientRepository;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;

/**
 * Client service
 *
 * @author Ludmar Ribeiro
 *
 */
@Service
public class ClientService {

    /**
     * Client's JPA repository
     */
    @Autowired
    private ClientRepository repository;

    /**
     * Saves a new Client
     *
     * @param {@link Client}
     */
    public void create(Client client) {
        if(repository.existsById(client.getId()))
            throw new ObjectExistsForIdException(client);

        repository.save(client);
    }

    /**
     * Updates a new Client
     *
     * @param {@link Client}
     */
    public void update(Client client) {
        if(!repository.existsById(client.getId()))
            throw new NotFoundException(client);

        repository.save(client);
    }

    /**
     * Read all Clients
     *
     * @return {@link List} of {@link Client}
     */
    public List<Client> getAll() {
        return repository.findAll();
    }

    /**
     * Read one {@link Client}
     *
     * @param id
     * @return {@link Client}
     */
    public Client get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Client.class, id));
    }
}
