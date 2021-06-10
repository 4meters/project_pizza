package com.pizzaserver.domain.dto;

public class UserRegisterDto {
    private String login;
    private String password; //sha-1 hash

    public UserRegisterDto(String login, String password) {
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
