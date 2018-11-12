package com.lud.delivery.cvrptw.restaurant.json;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.service.RestaurantService;

@Component
public class RestaurantGetterDeserializer extends JsonDeserializer<Restaurant>{

    @Autowired
    private RestaurantService service;

    @Override
    public Restaurant deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        Integer id = Integer.valueOf(parser.getIntValue());

        try {
            return service.get(id);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
