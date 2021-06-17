package com.pizzaserver.domain.dto;

/**
 * Response message of http request.
 */
public class ResponseMessage {

    String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
