package com.lud.delivery.cvrptw.route.calculator.data.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;

@Component
@ConfigurationPropertiesBinding
public class RestaurantConverter extends AbstractLocationConverter<Restaurant>{

}
