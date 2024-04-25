package com.softvider.provider.datasource.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(name = "softvider.postgres.enable", havingValue = "true", matchIfMissing = true)
public interface EmployeeService {
    public BaseResponse get();
    public BaseResponse insert(JsonNode data);
}
