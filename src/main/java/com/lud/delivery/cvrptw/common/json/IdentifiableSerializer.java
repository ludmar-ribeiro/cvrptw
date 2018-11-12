package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lud.delivery.cvrptw.common.domain.Identifiable;

@Component
public class IdentifiableSerializer extends JsonSerializer<Identifiable<?>>{

    @Override
    public void serialize(Identifiable<?> object, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(object.getId());
    }

}
