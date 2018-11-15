package com.lud.delivery.cvrptw.route.component.data.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.lud.delivery.cvrptw.route.component.data.RouteCalculatorTestLocationsData;
import com.lud.delivery.cvrptw.route.domain.Location;

public abstract class AbstractLocationConverter<T extends Location> implements Converter<String, T>{

    @Autowired
    private RouteCalculatorTestLocationsData data;

    @Override
    @SuppressWarnings("unchecked")
    public final T convert(String value) {
        EvaluationContext context = new StandardEvaluationContext(data);

        return (T) new SpelExpressionParser()
                .parseExpression(value)
                .getValue(context);
    }
}
