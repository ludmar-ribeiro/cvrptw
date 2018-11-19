package com.lud.delivery.cvrptw.common.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Get a formatting sample for a type
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class DefaultFormatSamplesProvider {

    /**
     * The map of formatting samples defined on the application properties 
     */
    @Value("#{${common.default-format.samples}}")
    private Map<Class<?>, String> samplesMap;

    /**
     * Returns the formatting sample for a clazz
     *
     * @param clazz
     * @return {@link String}
     */
    public String getSample(Class<?> clazz) {
        Class<?> key = samplesMap
                    .keySet()
                        .stream()
                            .filter(c -> clazz.isAssignableFrom(c))
                                .findFirst()
                    .orElseGet(() -> clazz);

        return samplesMap.get(key);
    }


}
