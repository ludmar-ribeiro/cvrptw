package com.lud.delivery.cvrptw.client.json;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.service.ClientService;

@Component
public class ClientGetterDeserializer extends JsonDeserializer<Client>{

    @Autowired
    private ClientService service;

    @Override
    public Client deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        Integer id = Integer.valueOf(parser.getIntValue());

        return service.get(id);
    }

}
