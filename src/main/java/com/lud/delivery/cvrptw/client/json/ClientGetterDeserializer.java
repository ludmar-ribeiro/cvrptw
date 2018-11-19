package com.lud.delivery.cvrptw.client.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.service.ClientService;
import com.lud.delivery.cvrptw.common.json.IdentifiableByIntegerDeserializer;

/**
 * Json deserializer to get a Client from its id
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class ClientGetterDeserializer extends IdentifiableByIntegerDeserializer<Client>{

    /**
     * Client Service
     */
    @Autowired
    private ClientService service;

    /**
     * Returns a Client from its id
     *
     * @return {@link Client}
     */
    @Override
    protected Client getObject(Integer id) {
        return service.get(id);
    }

}
