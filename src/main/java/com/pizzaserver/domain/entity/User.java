package com.pizzaserver.domain.entity;

import javax.persistence.*;

import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    public User(String login, String password, String token) {
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public User(){

    }

    private User(Builder builder) {
        login = builder.login;
        password = builder.password;
        token = builder.token;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static final class Builder {
        private String login;
        private String password;
        private String token;

        public Builder() {
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
