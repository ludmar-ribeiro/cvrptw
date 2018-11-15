package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lud.delivery.cvrptw.common.utils.DefaultDateTimeFormat;

@JsonComponent
public class DateTimeSerializer extends JsonSerializer<LocalDateTime>{

    @Autowired
    private DefaultDateTimeFormat defaultDateTimeFormat;

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(defaultDateTimeFormat.format(dateTime));
    }

}
