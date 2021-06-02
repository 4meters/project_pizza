package com.pizzaserver.domain.dto;

public class UserRegister {
    private String login;
    private String password;
    
    public UserRegister(String login, String password) {
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
