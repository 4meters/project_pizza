package com.pizzaserver.domain.dto;

/**
 * Class stores login token value
 */
public class UserLoginSuccessDto {

    private String token;

    public UserLoginSuccessDto(String token) {
        this.token = token;
    }

    public UserLoginSuccessDto(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }


    public static final class Builder {
        private String token;

        public Builder() {
        }

        public static Builder anUserLoginSuccessDto() {
            return new Builder();
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UserLoginSuccessDto build() {
            return new UserLoginSuccessDto(this);
        }
    }
}
