package com.pizzaserver.service;

import com.pizzaserver.domain.dto.UserLoginDto;
import com.pizzaserver.domain.dto.UserLoginSuccessDto;
import com.pizzaserver.domain.dto.UserChangePasswordDto;
import com.pizzaserver.domain.dto.UserRegisterDto;
import com.pizzaserver.domain.entity.User;

import java.util.List;

public interface UserService {

    boolean createUser(UserRegisterDto userRegisterDto);
    UserLoginSuccessDto loginUser(UserLoginDto userLoginDto);

    boolean checkTokenUser(String token);
    boolean checkTokenAdmin(String token);

    boolean deleteUser(UserLoginDto userLoginDto);

    boolean changePassword(UserChangePasswordDto userChangePasswordDto);

    //for testing
    List<User> readAll();

}
