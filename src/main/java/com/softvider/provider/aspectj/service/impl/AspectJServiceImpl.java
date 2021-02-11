package com.softvider.provider.aspectj.service.impl;

import com.softvider.provider.aspectj.service.AspectJService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AspectJServiceImpl implements AspectJService {
    private static final Logger log = LoggerFactory.getLogger(AspectJServiceImpl.class);

    @Override
    public Map<String, Object> Home(String request) {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("name", "Puthy");
        log.info("Response <= {}", jsonString);
        return jsonString;
    }
}
