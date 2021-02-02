package com.softvider.config.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.interceptor.KeyGenerator;
import java.lang.reflect.Method;
import java.util.List;

public class GetWithParamKeyGenerator implements KeyGenerator {
    private static final Logger log = LogManager.getLogger(GetWithParamKeyGenerator.class);

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(params);
            List<String> strings = mapper.readValue(jsonString, new TypeReference<List<String>>() {});
            for(String str: strings){
                stringBuilder.append("_").append(str);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("cache key => "+ stringBuilder.toString());
        return stringBuilder.toString();
    }
}
