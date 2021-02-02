package com.softvider.utils;

public class AppUtilException extends Exception {

    private final ExceptionBaseResponse response;

    public AppUtilException(ExceptionBaseResponse response) {
        this.response = response;
    }

    public ExceptionBaseResponse getTemplateStrategy() {
        return response;
    }
}
