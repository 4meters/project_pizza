package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.*;
import com.pizzaserver.domain.entity.User;
import com.pizzaserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin
@RequestMapping("/api")
public class UserApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class) ;
    private UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Api for registering new user
     * @param userRegisterDto login, password
     * @return true if user is created or false if not
     */
    @PostMapping(value = "/user/create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserRegisterDto userRegisterDto){
        boolean isCreated=userService.createUser(userRegisterDto);
        if(isCreated){
            return new ResponseEntity<Boolean>(isCreated, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Boolean>(isCreated, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Api for user log in
     * @param userLoginDto login, password
     * @return token or bad request if login or password is incorrect or user does not exists
     */
    @PostMapping(value = "/user/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto){
        UserLoginSuccessDto userLoginSuccessDto=userService.loginUser(userLoginDto);
        if(userLoginSuccessDto!=null){
            return new ResponseEntity<UserLoginSuccessDto>(userLoginSuccessDto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserLoginDto userLoginDto){
        boolean isDeleted = userService.deleteUser(userLoginDto);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user/change-password")
    public ResponseEntity<?> deleteUser(@RequestBody UserChangePasswordDto userChangePasswordDto){
        boolean isPasswordChanged=userService.changePassword(userChangePasswordDto);
        if(isPasswordChanged){
            return new ResponseEntity<UserLoginSuccessDto>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //for testing
    @GetMapping(value = "/user/test/readall")
    public ResponseEntity<UserListDto> getList(){
        List<User> u=userService.readAll();

        UserListDto u2= new UserListDto(u);
        return new ResponseEntity<UserListDto>(u2, HttpStatus.OK);
    }

}
