package com.lud.delivery.cvrptw.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The default date time formatter
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class DefaultDateTimeFormat {

    /**
     * The date time formatter
     */
    private DateTimeFormatter formatter;

    /**
     * Creates the formatter from a injected pattern 
     *
     * @param pattern
     */
    @Autowired
    public void setDefaultDateFromat(@Value("${common.default-format.date}") String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * Format a {@link LocalDateTime} using the dafault format
     *
     * @param dateTime
     * @return {@link String}
     */
    public String format(LocalDateTime dateTime) {
        return formatter.format(dateTime);
    }

    /**
     * Parses a {@link String} to {@link LocalDateTime} using the default format
     *
     * @param value
     * @return {@link LocalDateTime}
     */
    public LocalDateTime parse(String value) {
        return LocalDateTime.parse(value, formatter);
    }
}
