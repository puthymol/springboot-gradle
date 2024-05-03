package com.softvider.provider.applicationevent.service.impl;

import com.softvider.provider.applicationevent.listener.AppEvent;
import com.softvider.provider.applicationevent.service.ApplicationEventService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApplicationEventServiceImpl implements ApplicationEventService {

    @Inject
    private ApplicationEventPublisher eventPublisher;

    @Override
    public Map<String, Object> home(String request) {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Puthy");
        eventPublisher.publishEvent(new AppEvent("Puthy"));
        return response;
    }
}
