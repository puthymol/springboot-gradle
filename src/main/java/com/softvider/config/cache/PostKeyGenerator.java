package com.softvider.config.cache;

import com.softvider.provider.cache.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class PostKeyGenerator implements KeyGenerator {
    private static final Logger log = LogManager.getLogger(PostKeyGenerator.class);

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        Book request = (Book) params[0];
        stringBuilder.append("_").append(request.getName());
        stringBuilder.append("_").append(request.getIsbn());
        log.info(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
