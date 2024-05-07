package com.softvider.provider.jmeter;

import com.softvider.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/jmeter")
public class JMeterController {
    private static final Logger log = LoggerFactory.getLogger(JMeterController.class);

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getSession(@RequestBody Map<String, Object> request) throws InterruptedException {
        log.info("Request -> {}", AppUtil.toJSON(request));
        Map<String, Object> response = new HashMap<>();
        Thread.sleep(1000);
        response.put("username", request.get("username").toString());
        response.put("session", UUID.randomUUID());
        log.info("Response -> {}", AppUtil.toJSON(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> submit(@RequestBody Map<String, Object> request) throws InterruptedException {
        log.info("Request -> {}", AppUtil.toJSON(request));
        Thread.sleep(2000);
        request.put("status", "SUCCESS");
        log.info("Response -> {}", AppUtil.toJSON(request));
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
