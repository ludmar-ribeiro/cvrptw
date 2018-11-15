package com.lud.delivery.cvrptw.route.component.data.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.Location;

@Component
@ConfigurationPropertiesBinding
public class LocationConverter extends AbstractLocationConverter<Location>{

}
