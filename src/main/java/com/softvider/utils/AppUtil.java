package com.softvider.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

public class AppUtil {
    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    public static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LogManager.getLogger(AppUtil.class);

    public static  <T> T convertValidate(Object object, Class<T> clazz) throws AppUtilValidationException {
        T request = objectMapper.convertValue(object, clazz);
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        if(!violations.isEmpty()) {
            throw new AppUtilValidationException(violations);
        }
        return request;
    }

    public static String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException ex) {
            log.error("IOException: " + ex.getMessage());
            return null;
        }
    }

    public static <T> T convert(Object object, Class<T> clazz) {
        return objectMapper.convertValue(object, clazz);
    }
}
