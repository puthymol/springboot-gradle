package com.softvider.provider.applicationevent.controller;

import com.softvider.provider.applicationevent.service.ApplicationEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.util.Map;

@RestController
@RequestMapping(value = "/application-event")
public class ApplicationEventController {
    private static final Logger log = LoggerFactory.getLogger(ApplicationEventController.class);

    @Inject
    private ApplicationEventService applicationEventService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> home() {
        log.info("Request...");
        Map<String, Object> response = applicationEventService.home("Puthy");
        log.info("Response <= {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
