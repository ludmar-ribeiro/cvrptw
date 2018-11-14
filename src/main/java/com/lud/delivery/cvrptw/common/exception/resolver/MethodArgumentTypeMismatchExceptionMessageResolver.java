package com.lud.delivery.cvrptw.common.exception.resolver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lud.delivery.cvrptw.common.exception.proxy.MessageResolvableRawExceptionProxy;

@Component
public class MethodArgumentTypeMismatchExceptionMessageResolver implements MessageResolver<MethodArgumentTypeMismatchException> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SimpleDateFormat defaultDateFormat;

    @Override
    public String resolveMessage(MethodArgumentTypeMismatchException exception) {
        String property = exception.getName();
        Object value = exception.getValue();
        String type = exception.getRequiredType().getSimpleName();
        String expectedFormat = null;

        String alternativeMessageCodeSufix = "";
        if(exception.getRequiredType().isAssignableFrom(Date.class)) {
            alternativeMessageCodeSufix = ".withFormat";
            expectedFormat = defaultDateFormat.format(new Date());
        }

        Object[] args = new Object[] {
                property,
                value,
                type,
                expectedFormat
        };

        return messageSource.getMessage(new MessageResolvableRawExceptionProxy(exception, alternativeMessageCodeSufix, args), Locale.getDefault());
    }

}
