package com.lud.delivery.cvrptw.client.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.service.ClientService;
import com.lud.delivery.cvrptw.common.json.IdentifiableByIntegerDeserializer;

@Component
public class ClientGetterDeserializer extends IdentifiableByIntegerDeserializer<Client>{

    @Autowired
    private ClientService service;

    @Override
    protected Client getObject(Integer id) {
        return service.get(id);
    }

}
