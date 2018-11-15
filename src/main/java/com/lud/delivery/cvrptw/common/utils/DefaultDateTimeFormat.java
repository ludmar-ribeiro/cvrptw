package com.lud.delivery.cvrptw.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultDateTimeFormat {

    private DateTimeFormatter formatter;

    @Autowired
    public void setDefaultDateFromat(@Value("${common.default-format.date}") String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public String format(LocalDateTime dateTime) {
        return formatter.format(dateTime);
    }

    public LocalDateTime parse(String value) {
        return LocalDateTime.parse(value, formatter);
    }
}
