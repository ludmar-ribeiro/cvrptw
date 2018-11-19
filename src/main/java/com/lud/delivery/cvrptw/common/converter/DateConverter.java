package com.lud.delivery.cvrptw.common.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.ResolvableParseException;
import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.common.utils.DefaultDateTimeFormat;

/**
 * Converter to extract Date time from properties files using the tokens:
 *
 * now: Current Time
 * now.second: Current Time ignoring milliseconds
 * now.minute: Current Time ignoring seconds or less
 * now.hour: Current Time ignoring minutes or less
 *
 * add milliseconds: + 999999...
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
@ConfigurationPropertiesBinding
public class DateConverter implements Converter<String, LocalDateTime>{

    /**
     * The RegEx pattern to identify the tokens
     */
    private static final String PATTERN = "now(.(hour|minute|second)){0,1}[\\s]*([\\+]{1}[\\s]*[0-9]+){0,1}";

    /**
     * The default expected date time format 
     */
    @Autowired
    private DefaultDateTimeFormat defaultDateTimeFormat;

    /**
     * Converts a {@link String} to {@link LocalDateTime} considering
     * the tokens: now, now.second, now.minute, now.hour, "token" + 9999999...
     *
     * @param value
     * @return {@link LocalDateTime}
     */
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

    /**
     * Parse the {@link String} to {@link LocalDateTime}
     *
     * @param value
     * @return {@link LocalDateTime}
     */
    private LocalDateTime parse(String value) {
        String[] parts = value.replaceAll("\\s", "").split("\\+");
        LocalDateTime dateTime = LocalDateTime.now();

        if(value.contains(".hour"))
            dateTime = DateTimeUtils.ignoreMinutes(dateTime);

        if(value.contains(".minute"))
            dateTime = DateTimeUtils.ignoreSeconds(dateTime);

        if(value.contains(".second"))
            dateTime = DateTimeUtils.ignoreMilliseconds(dateTime);

        if(parts.length == 2)
            dateTime = DateTimeUtils.addMilliseconds(dateTime, Double.valueOf(parts[1]));

        return dateTime;
    }

}
