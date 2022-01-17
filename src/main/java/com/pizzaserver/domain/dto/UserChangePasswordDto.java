package com.pizzaserver.domain.dto;

public class UserChangePasswordDto {//change on swaggerhub
    private String login;
    private String password;
    private String newPassword;

    public UserChangePasswordDto(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.newPassword = builder.newPassword;
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


    public static final class Builder {
        private String login;
        private String password; //sha-1 hash?
        private String newPassword;

        private Builder() {
        }

        public static Builder anUserChangePasswordDto() {
            return new Builder();
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder newPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public UserChangePasswordDto build() {
            return new UserChangePasswordDto(this);
        }
    }
}
