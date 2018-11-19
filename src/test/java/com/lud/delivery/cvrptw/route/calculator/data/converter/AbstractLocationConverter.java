package com.lud.delivery.cvrptw.route.calculator.data.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.lud.delivery.cvrptw.route.calculator.data.RouteCalculatorTestLocationsData;
import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Abstract converter to extract dynamic data from properties file,
 * most specifically a Location.
 * 
 *
 * @author Ludmar Ribeiro
 *
 * @param <T> Class that extends Location
 */
public abstract class AbstractLocationConverter<T extends Location> implements Converter<String, T>{

    /**
     * Source of data
     */
    @Autowired
    private RouteCalculatorTestLocationsData data;

    /**
     * Conversion logic to extract info from "data" by reflection
     * using SpEL.  
     */
    @Override
    @SuppressWarnings("unchecked")
    public final T convert(String value) {
        EvaluationContext context = new StandardEvaluationContext(data);

        return (T) new SpelExpressionParser()
                .parseExpression(value)
                .getValue(context);
    }
}
