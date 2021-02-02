package com.softvider.provider.cache.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softvider.provider.cache.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {
    private static final Logger log = LogManager.getLogger(CacheService.class);

    public Map<String, Object> GetData(String param) {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider");
        if(param != null){
            jsonString.put("name", param);
        }
        log.info("Response => {}", jsonString);
        return jsonString;
    }

    public Map<String, Object> PostData(Book book) {
        Map<String, Object> jsonString = new HashMap<>();
        ObjectMapper oMapper = new ObjectMapper();
        try {
            String str = oMapper.writeValueAsString(book);
            jsonString = oMapper.readValue(str, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("Response <= {}", jsonString);
        return jsonString;
    }
}
