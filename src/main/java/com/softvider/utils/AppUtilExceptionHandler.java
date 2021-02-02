package com.softvider.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppUtilExceptionHandler {

    @ExceptionHandler(AppUtilException.class)
    public ResponseEntity<ExceptionBaseResponse> handleMethodBadCredentialsException(AppUtilException exception) {
        return new ResponseEntity<>(exception.getTemplateStrategy(), HttpStatus.OK);
    }
}
