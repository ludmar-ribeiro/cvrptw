package com.lud.delivery.cvrptw.common.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultFormatSamplesProvider {

    @Value("#{${common.default-format.samples}}")
    private Map<Class<?>, String> samplesMap;

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
