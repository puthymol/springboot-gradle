package com.softvider.model;

import com.fasterxml.jackson.databind.JsonNode;

public class BaseResponse {

    private int statusCode;
    private String statusMessage;
    private JsonNode data;

    public BaseResponse(){
        this.statusCode = 200;
        this.statusMessage = "Success";
    }

    public BaseResponse(JsonNode data){
        this.statusCode = 200;
        this.statusMessage = "Success";
        this.data = data;
    }

    public BaseResponse(String statusMessage){
        this.statusCode = 201;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }
}
