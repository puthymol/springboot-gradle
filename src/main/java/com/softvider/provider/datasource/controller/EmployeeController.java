package com.softvider.provider.datasource.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;
import com.softvider.provider.datasource.service.EmployeeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/datasource")
@ConditionalOnProperty(name = "softvider.postgres.enabled", havingValue = "true", matchIfMissing = true)
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

    @RequestMapping(value = "/employee/get", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> get() {
        BaseResponse result = employeeService.get();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/insert", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> insert(@RequestBody JsonNode jsonNode) {
        BaseResponse result = employeeService.insert(jsonNode);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
