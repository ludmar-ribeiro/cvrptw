package com.lud.delivery.cvrptw.common.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.common.utils.DefaultDateTimeFormat;
import com.lud.delivery.cvrptw.exception.ResolvableParseException;

@Component
@ConfigurationPropertiesBinding
public class DateConverter implements Converter<String, LocalDateTime>{

    private final String PATTERN = "now[\\s]*([\\+]{1}[\\s]*[0-9]+){0,1}";

    @Autowired
    private DefaultDateTimeFormat defaultDateTimeFormat;

    @Override
    public LocalDateTime convert(String value) {
        if(value.matches(PATTERN)) {
            return parse(value);
        }

        try {
            return defaultDateTimeFormat.parse(value);
        } catch (Exception e) {
        }

        try {
            return LocalDateTime.parse(value);
        } catch (Exception e) {
            throw new ResolvableParseException(value, LocalDateTime.class.getSimpleName());
        }
    }

    private LocalDateTime parse(String value) {
        String[] parts = value.replaceAll("\\s", "").split("\\+");
        LocalDateTime dateTime = LocalDateTime.now();

        if(parts.length == 2)
            dateTime = DateTimeUtils.addMilliseconds(dateTime, Double.valueOf(parts[1]));

        return dateTime;
    }

}
