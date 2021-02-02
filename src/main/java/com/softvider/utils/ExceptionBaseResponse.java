package com.softvider.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;

public class ExceptionBaseResponse extends BaseResponse {
    private final JsonNode errors;

    public ExceptionBaseResponse(JsonNode errors) {
        super("Error");
        this.errors = errors;
    }

    public JsonNode getErrors() {
        return errors;
    }
}
