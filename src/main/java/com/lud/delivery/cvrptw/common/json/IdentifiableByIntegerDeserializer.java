package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lud.delivery.cvrptw.common.domain.Identifiable;

public abstract class IdentifiableByIntegerDeserializer<T extends Identifiable<Integer>> extends JsonDeserializer<T>{

    @Override
    public final T deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        Integer id =  parseId(parser);

        return getObject(id);
    }

    protected abstract T getObject(Integer id);

    private final Integer parseId(JsonParser parser) throws IOException, InvalidFormatException {
        String value = parser.getValueAsString();

        try {
            return Double.valueOf(value).intValue();
        }catch (Exception e) {
            throw InvalidFormatException.from(parser, String.format("The value %s specified is not a valid id", value), value, Integer.class);
        }
    }
}
