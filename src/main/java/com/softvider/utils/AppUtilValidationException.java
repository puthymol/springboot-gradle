package com.softvider.utils;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AppUtilValidationException extends Exception {

    private final Map<String, Object> errors = new HashMap<>();

    public <T> AppUtilValidationException(Set<ConstraintViolation<T>> violations) {
        super("Fail");
        for (ConstraintViolation<T> error : violations) {
            this.errors.put(error.getPropertyPath().toString(), error.getMessage());
        }
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
