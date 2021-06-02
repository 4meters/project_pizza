package com.pizzaserver.domain.object;

public class User {
    private String login;
    private String pass;
    private String token;

    public User() {
    }

    public User(String login, String pass, String token) {
        this.login = login;
        this.pass = pass;
        this.token = token;
    }

    public User(String[] user){
        this.login=user[0];
        this.pass=user[1];
        this.token=user[2];
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getToken() {
        return token;
    }
}
