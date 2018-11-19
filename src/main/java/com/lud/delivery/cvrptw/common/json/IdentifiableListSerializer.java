package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lud.delivery.cvrptw.common.domain.Identifiable;

/**
 * Json serializer to parse a {@link List} of {@link Identifiable} as a list of its id
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class IdentifiableListSerializer extends JsonSerializer<List<Identifiable<?>>>{

    /**
     * Parses a {@link List} of {@link Identifiable} to a list of its id and
     * writes it to a Json
     *
     * @param list
     * @param generator
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(List<Identifiable<?>> list, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartArray();

        for(Identifiable<?> identifiable:list)
            generator.writeObject(identifiable.getId());

        generator.writeEndArray();
    }

}
