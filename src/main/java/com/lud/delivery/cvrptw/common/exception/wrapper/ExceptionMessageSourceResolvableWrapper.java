package com.lud.delivery.cvrptw.common.exception.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;

public class ExceptionMessageSourceResolvableWrapper implements MessageSourceResolvable{

    private Exception exception;
    private String codeSufix;
    private Object[] args;

    public ExceptionMessageSourceResolvableWrapper(Exception exception, String codeSufix, Object... args) {
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

