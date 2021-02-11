package com.softvider.provider.threadpool.service.impl;

import com.softvider.provider.threadpool.listener.AppEvent;
import com.softvider.provider.threadpool.service.ThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolServiceImpl.class);

    @Inject
    private ApplicationEventPublisher eventPublisher;

    @Override
    public Map<String, Object> Home(String request) {
        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("name", "Puthy");
        eventPublisher.publishEvent(new AppEvent("Puthy"));
        log.info("Response <= {}", jsonString);
        return jsonString;
    }
}
