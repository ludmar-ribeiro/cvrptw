package com.lud.delivery.cvrptw.common.exception.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;

/**
 * Wraps a exception for that could be used as a {@link MessageSourceResolvable}  
 *
 * @author Ludmar Ribeiro
 *
 */
public class ExceptionMessageSourceResolvableWrapper implements MessageSourceResolvable{

    private Exception exception;
    private String codeSufix;
    private Object[] args;

    public ExceptionMessageSourceResolvableWrapper(Exception exception, String codeSufix, Object... args) {
        this.exception = exception;
        this.codeSufix = codeSufix;
        this.args = args;
    }

    /**
     * Returns the message codes
     *
     * @return an array of {@link String}
     */
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

    /**
     * Returns the arguments to be used by the localization engine
     *
     * @return an array of {@link Object}
     */
    @Override
    public Object[] getArguments() {
        return args;
    }
}

