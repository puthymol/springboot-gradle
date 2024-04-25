package com.softvider.provider.threadpool.controller;

import com.softvider.provider.threadpool.service.ThreadPoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.util.Map;

@RestController
@RequestMapping(value = "/threadpool")
public class ThreadPoolController {

    @Inject
    private ThreadPoolService threadPoolService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> result = threadPoolService.home("Puthy");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
