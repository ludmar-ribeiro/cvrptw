package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lud.delivery.cvrptw.common.domain.Identifiable;

/**
 * Json serializer to parse a {@link Identifiable} as its id
 *
 * @author Ludmar Ribeiro
 *
 */@Component
public class IdentifiableSerializer extends JsonSerializer<Identifiable<?>>{

     /**
      * Parses a {@link Identifiable} to its id and
      * writes it to a Json
      *
      * @param identifiable
      * @param generator
      * @param provider
      * @throws IOException
      */
    @Override
    public void serialize(Identifiable<?> identifiable, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(identifiable.getId());
    }
}
