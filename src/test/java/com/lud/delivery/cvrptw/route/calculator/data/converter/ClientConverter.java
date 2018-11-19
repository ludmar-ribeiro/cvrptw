package com.lud.delivery.cvrptw.route.calculator.data.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;

/**
 * Abstract converter to extract dynamic data from properties file,
 * most specifically a {@link Client} (Location).
 * 
 * @author Ludmar Ribeiro
 * 
 * @see com.lud.delivery.cvrptw.route.calculator.data.converter.AbstractLocationConverter
 * */
@Component
@ConfigurationPropertiesBinding
public class ClientConverter extends AbstractLocationConverter<Client>{

}
