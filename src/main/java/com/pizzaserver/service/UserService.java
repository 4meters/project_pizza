package com.pizzaserver.service;

import com.pizzaserver.domain.dto.UserLoginDto;
import com.pizzaserver.domain.dto.UserLoginSuccessDto;
import com.pizzaserver.domain.dto.UserRegisterDto;

public interface UserService {

    boolean createUser(UserRegisterDto userRegisterDto);
    UserLoginSuccessDto loginUser(UserLoginDto userLoginDto);

    boolean checkTokenUser(String token);
    boolean checkTokenAdmin(String token);

    //for testing
    //List<User> readAll();

}
