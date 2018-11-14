package com.lud.delivery.cvrptw.common.config;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public SimpleDateFormat defaultDateFormat(@Value("${common.default-format.date}") String pattern) {
        return new SimpleDateFormat(pattern);
    }
}
