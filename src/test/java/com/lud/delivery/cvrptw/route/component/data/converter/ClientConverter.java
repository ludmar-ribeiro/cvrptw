package com.lud.delivery.cvrptw.route.component.data.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.client.domain.Client;

@Component
@ConfigurationPropertiesBinding
public class ClientConverter extends AbstractLocationConverter<Client>{

}
