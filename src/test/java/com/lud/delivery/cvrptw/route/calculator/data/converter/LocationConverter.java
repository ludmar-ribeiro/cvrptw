package com.lud.delivery.cvrptw.route.calculator.data.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Abstract converter to extract dynamic data from properties file,
 * most specifically a Location.
 * 
 * @author Ludmar Ribeiro
 * 
 * @see com.lud.delivery.cvrptw.route.calculator.data.converter.AbstractLocationConverter
 * */
@Component
@ConfigurationPropertiesBinding
public class LocationConverter extends AbstractLocationConverter<Location>{

}
