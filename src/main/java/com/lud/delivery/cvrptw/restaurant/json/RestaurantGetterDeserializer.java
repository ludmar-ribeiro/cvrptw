package com.lud.delivery.cvrptw.restaurant.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.json.IdentifiableByIntegerDeserializer;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.service.RestaurantService;

@Component
public class RestaurantGetterDeserializer extends IdentifiableByIntegerDeserializer<Restaurant>{

    @Autowired
    private RestaurantService service;

    @Override
    protected Restaurant getObject(Integer id) {
        return service.get(id);
    }

}
