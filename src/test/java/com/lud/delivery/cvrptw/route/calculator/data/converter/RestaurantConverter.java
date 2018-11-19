package com.lud.delivery.cvrptw.route.calculator.data.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;

/**
 * Abstract converter to extract dynamic data from properties file,
 * most specifically a {@link Restaurant} (Location).
 * 
 * @author Ludmar Ribeiro
 * 
 * @see com.lud.delivery.cvrptw.route.calculator.data.converter.AbstractLocationConverter
 * */
@Component
@ConfigurationPropertiesBinding
public class RestaurantConverter extends AbstractLocationConverter<Restaurant>{

}
