package com.softvider.provider.aspectj.controller;

import com.softvider.provider.aspectj.service.AspectJService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Map;

@RestController
@RequestMapping(value = "/aspectj")
public class AspectJController {

    @Inject
    private AspectJService aspectJService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> Home() {
        Map<String, Object> result = aspectJService.Home("Puthy");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
