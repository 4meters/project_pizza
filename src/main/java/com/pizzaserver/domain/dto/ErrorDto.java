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
}
