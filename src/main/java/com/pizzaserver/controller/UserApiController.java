package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.UserListDto;
import com.pizzaserver.domain.dto.UserLoginDto;
import com.pizzaserver.domain.dto.UserLoginSuccessDto;
import com.pizzaserver.domain.dto.UserRegisterDto;
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

    @PostMapping(value = "/user/create")
    public ResponseEntity<Boolean> createUser(@RequestBody UserRegisterDto userRegisterDto){
        boolean isCreated=userService.createUser(userRegisterDto);
        return new ResponseEntity<Boolean>(isCreated, HttpStatus.CREATED);

    }
    @PostMapping(value = "/user/login")
    public ResponseEntity<UserLoginSuccessDto> loginUser(@RequestBody UserLoginDto userLoginDto){
        UserLoginSuccessDto userLoginSuccessDto=userService.loginUser(userLoginDto);
        if(userLoginSuccessDto!=null){
            return new ResponseEntity<UserLoginSuccessDto>(userLoginSuccessDto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<UserLoginSuccessDto>((UserLoginSuccessDto) null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/test/readall")
    public ResponseEntity<UserListDto> getList(){
        List<User> u=userService.readAll();

        UserListDto u2= new UserListDto(u);
        return new ResponseEntity<UserListDto>(u2, HttpStatus.OK);
    }

}
