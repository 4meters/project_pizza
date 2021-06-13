package com.pizzaserver.domain.dto;

public class UserLoginSuccessDto {
    private String token;

    public UserLoginSuccessDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
