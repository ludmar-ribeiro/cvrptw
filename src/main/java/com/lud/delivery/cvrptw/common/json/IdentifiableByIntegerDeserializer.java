package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lud.delivery.cvrptw.common.domain.Identifiable;

/**
 * Abstract Json deserializer to get any {@link Identifiable} by {@link Integer} using its id
 *
 * @author Ludmar Ribeiro
 *
 * @param <T> the Class that id {@link Identifiable} by {@link Integer}
 */
public abstract class IdentifiableByIntegerDeserializer<T extends Identifiable<Integer>> extends JsonDeserializer<T>{

    /**
     * Get the {@link Identifiable} by an id extracted from a Json 
     *
     * @param parser
     * @param context
     * @return T
     * @throws IOException, {@link JsonProcessingException}
     */
    @Override
    public final T deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        Integer id =  parseId(parser);

        return getObject(id);
    }

    /**
     * Gets the {@link Identifiable} by its id
     *
     * @param id
     * @return T
     */
    protected abstract T getObject(Integer id);

    /**
     * Parses the id extracted from the json 
     *
     * @param parser
     * @return {@link Integer}
     * @throws IOException
     * @throws InvalidFormatException
     */
    private final Integer parseId(JsonParser parser) throws IOException, InvalidFormatException {
        String value = parser.getValueAsString();

        try {
            return Double.valueOf(value).intValue();
        }catch (Exception e) {
            throw InvalidFormatException.from(parser, String.format("The value %s specified is not a valid id", value), value, Integer.class);
        }
    }
}
