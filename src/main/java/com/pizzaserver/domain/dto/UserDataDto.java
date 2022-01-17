package com.pizzaserver.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Stores user information for generating pdf receipt and token to verify if user is logged in and has permission
 * to do it.
 */
@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDataDto implements Serializable {

    String firstName;
    String lastName;
    String orderList;
    String token;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOrderList() {
        return orderList;
    }

    public String getToken() {
        return token;
    }

    public UserDataDto(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.orderList = builder.orderList;
        this.token = builder.token;
    }

    public static final class Builder {
        String firstName;
        String lastName;
        String orderList;
        String token;

        private Builder() {
        }

        public static Builder anUserDataDto() {
            return new Builder();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder orderList(String orderList) {
            this.orderList = orderList;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UserDataDto build() {
            return new UserDataDto(this);
        }
    }
}
