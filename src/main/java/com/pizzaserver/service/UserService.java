package com.pizzaserver.service;

import com.pizzaserver.domain.dto.UserLoginDto;
import com.pizzaserver.domain.dto.UserLoginSuccessDto;
import com.pizzaserver.domain.dto.UserRegisterDto;
import com.pizzaserver.domain.entity.User;

import java.util.List;

public interface UserService {
    //UserLoginSuccessDto loginUser();
    //boolean registerUser();
    //
    //test only
    String readUser(String login);
    List<User> readAll();
    //test only


    //String checkUser(String login);
    boolean createUser(UserRegisterDto userRegisterDto);
    UserLoginSuccessDto loginUser(UserLoginDto userLoginDto);

    boolean checkTokenUser(String token);

}
