package com.softvider.provider.validation.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;
import com.softvider.provider.validation.model.adapter.StudentAdapter;
import com.softvider.utils.AppUtil;
import com.softvider.utils.AppUtilException;
import com.softvider.utils.AppUtilValidationException;
import com.softvider.utils.ExceptionBaseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/validation")
public class StudentController {
    private static final Logger log = LogManager.getLogger(StudentController.class);

    @RequestMapping(value = "/student/get", method = RequestMethod.POST)
    public BaseResponse Get(@RequestBody JsonNode jsonNode) throws AppUtilException {
        try {
            StudentAdapter request = AppUtil.convertValidate(jsonNode, StudentAdapter.class);
            log.info("Request => {}", AppUtil.toJSON(request));
            return new BaseResponse(AppUtil.toJSON(request));
        } catch (AppUtilValidationException e) {
            throw new AppUtilException(new ExceptionBaseResponse(AppUtil.convert(e.getErrors(), JsonNode.class)));
        }
    }

    @ExceptionHandler(AppUtilException.class)
    public ResponseEntity<ExceptionBaseResponse> handleMethodArgumentNotValidException(AppUtilException exception) {
        return new ResponseEntity<>(exception.getTemplateStrategy(), HttpStatus.OK);
    }
}
