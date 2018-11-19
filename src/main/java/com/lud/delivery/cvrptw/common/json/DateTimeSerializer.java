package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lud.delivery.cvrptw.common.utils.DefaultDateTimeFormat;

/**
 * Json serializer for any {@link LocalDateTime} to formt it the the default
 * date time format 
 *
 * @author Ludmar Ribeiro
 *
 */
@JsonComponent
public class DateTimeSerializer extends JsonSerializer<LocalDateTime>{

    /**
     * Default date time format
     */
    @Autowired
    private DefaultDateTimeFormat defaultDateTimeFormat;

    /**
     * Parses a {@link LocalDateTime} to {@link String} using
     * the default date time format and write it to a Json
     *
     * @param dateTime
     * @param generator
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(defaultDateTimeFormat.format(dateTime));
    }

}
