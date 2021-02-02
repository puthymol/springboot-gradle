package com.softvider.provider.datasource.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;

public interface EmployeeService {
    public BaseResponse get();
    public BaseResponse Insert(JsonNode data);
}
