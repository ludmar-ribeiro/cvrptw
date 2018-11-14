package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.lud.delivery.cvrptw.common.utils.DefaultFormatSamplesProvider;

@Component
public class MessageSourceResolvableForExceptionFactory {

    @Autowired
    private DefaultFormatSamplesProvider defaultFormatSamplesProvider;

    public MessageSourceResolvable create(Exception exception) {
        return new MessageSourceResolvableForException(exception, ".withOriginalMessage", exception.getMessage());
    }

    public MessageSourceResolvable create(JsonParseException exception) {
        String[] lines = exception.getMessage().split("\n");

        return new MessageSourceResolvableForException(exception, null, lines[0]);
    }

    public MessageSourceResolvableForException create(MethodArgumentTypeMismatchException exception) {

        String expectedFormat = getExpectedFormatSample(exception.getRequiredType());

        return new MessageSourceResolvableForException(
                exception,
                getSufixWithFormat(expectedFormat),
                exception.getName(),
                exception.getValue(),
                exception.getRequiredType().getSimpleName(),
                expectedFormat);
    }

    public MessageSourceResolvableForException create(InvalidFormatException exception) {

        String expectedFormat = getExpectedFormatSample(exception.getTargetType());

        return new MessageSourceResolvableForException(
                exception,
                getSufixWithFormat(expectedFormat),
                exception.getValue(),
                exception.getTargetType().getSimpleName(),
                expectedFormat);
    }

    public MessageSourceResolvableForException create(MismatchedInputException exception) {

        String expectedFormat = getExpectedFormatSample(exception.getTargetType());

        return new MessageSourceResolvableForException(
                exception,
                getSufixWithFormat(expectedFormat),
                exception.getTargetType().getSimpleName(),
                expectedFormat);
    }

    private String getSufixWithFormat(String expectedFormat) {
        return (expectedFormat != null ? ".withFormat" : null);
    }

    private String getExpectedFormatSample(Class<?> clazz) {
        return defaultFormatSamplesProvider.getSample(clazz);
    }

    public static class MessageSourceResolvableForException implements MessageSourceResolvable{

        private Exception exception;
        private String codeSufix;
        private Object[] args;

        private MessageSourceResolvableForException(Exception exception, String codeSufix, Object... args) {
            this.exception = exception;
            this.codeSufix = codeSufix;
            this.args = args;
        }

        @Override
        public String[] getCodes() {
            List<String> codeList = new ArrayList<>();

            if(codeSufix != null) {
                codeList.add(exception.getClass().getName().concat(codeSufix));
                codeList.add(exception.getClass().getSimpleName().concat(codeSufix));
            }

            codeList.add(exception.getClass().getName());
            codeList.add(exception.getClass().getSimpleName());

            codeList.add("common.exception.unknown");

            return codeList.toArray(new String[]{});
        }

        @Override
        public Object[] getArguments() {
            return args;
        }
    }

}
