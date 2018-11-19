package com.lud.delivery.cvrptw.restaurant.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.json.IdentifiableByIntegerDeserializer;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.service.RestaurantService;

/**
 * Json deserializer to get a Restaurant from its id
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class RestaurantGetterDeserializer extends IdentifiableByIntegerDeserializer<Restaurant>{

    /**
     * Restaurant Service
     */
    @Autowired
    private RestaurantService service;

    /**
     * Returns a Restaurant from its id
     *
     * @return {@link Restaurant}
     */
    @Override
    protected Restaurant getObject(Integer id) {
        return service.get(id);
    }

}
