package com.pizzaserver.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDto {
    String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public static final class Builder {
        String errorMessage;

        private Builder() {
        }

        public static Builder anErrorDto() {
            return new Builder();
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(errorMessage);
        }
    }
}
