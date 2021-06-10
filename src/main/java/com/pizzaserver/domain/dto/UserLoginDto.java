package com.pizzaserver.domain.dto;

public class UserLoginDto {
    private String login;
    private String password; //sha-1 hash

    public UserLoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
