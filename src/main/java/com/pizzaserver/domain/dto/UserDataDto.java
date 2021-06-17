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
}
