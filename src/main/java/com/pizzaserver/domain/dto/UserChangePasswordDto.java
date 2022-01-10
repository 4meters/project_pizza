package com.pizzaserver.domain.dto;

public class UserChangePasswordDto {//change on swaggerhub
    private String login;
    private String password; //sha-1 hash?
    private String newPassword;

    public UserChangePasswordDto(String login, String password, String newPassword) {
        this.login = login;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
