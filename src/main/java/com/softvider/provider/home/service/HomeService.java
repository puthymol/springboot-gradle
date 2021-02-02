package com.softvider.provider.home.service;

import com.softvider.utils.AppUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService {
    private static final Logger log = LogManager.getLogger(HomeService.class);

    public Map<String, Object> Home() {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("provider", "Softvider");
        log.info("Response => {}", AppUtil.toJSON(jsonString));
        return jsonString;
    }
}
