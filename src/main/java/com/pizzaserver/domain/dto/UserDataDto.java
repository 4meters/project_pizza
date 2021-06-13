package com.pizzaserver.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Klasa odpowidzialna jest za przetrzymywanie podstawowych informacji o uzytkowniku.
 */
                                                    /* lombok: */
@Getter                                             /* automatyczne generowanie getterow dla pol */
@ToString                                           /* automatyczne generowanie metody toString */
@NoArgsConstructor                                  /* automatyczne generowanie konstruktora bezparametrowego */
@FieldDefaults(level = AccessLevel.PRIVATE)         /* automatyczne generowanie zasiegu dla pol */
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
