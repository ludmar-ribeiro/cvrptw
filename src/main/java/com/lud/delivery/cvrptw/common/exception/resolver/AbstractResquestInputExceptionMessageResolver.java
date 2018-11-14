package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.beans.factory.annotation.Autowired;

import com.lud.delivery.cvrptw.common.utils.DefaultFormatSamplesProvider;

public abstract class AbstractResquestInputExceptionMessageResolver<T extends Exception> extends AbstractMessageResolver<T> {

    @Autowired
    private DefaultFormatSamplesProvider defaultFormatSamplesProvider;

    @Override
    protected String getCodeSufix(T exception) {
        return getExpectedFormat(exception) != null ? ".withFormat": null;
    }

    protected final String getExpectedFormat(T exception) {
        return defaultFormatSamplesProvider.getSample(getRequiredType(exception));
    }

    protected abstract Class<?> getRequiredType(T exception);
}
