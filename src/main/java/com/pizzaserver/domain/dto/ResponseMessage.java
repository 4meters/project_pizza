package com.pizzaserver.domain.dto;

/**
 * Response message of http request.
 */
public class ResponseMessage {

    String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private ResponseMessage(Builder builder) {
        this.responseMessage = builder.responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }


    public static final class Builder {
        String responseMessage;

        public Builder() {
        }

        public static Builder aResponseMessage() {
            return new Builder();
        }

        public Builder responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public ResponseMessage build() {
            return new ResponseMessage(this);
        }
    }
}
