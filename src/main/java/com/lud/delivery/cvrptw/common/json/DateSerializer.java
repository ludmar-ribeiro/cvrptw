package com.lud.delivery.cvrptw.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class DateSerializer extends JsonSerializer<Date>{

    @Autowired
    private SimpleDateFormat defaultDateFormat;

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(defaultDateFormat.format(date));
    }

}
