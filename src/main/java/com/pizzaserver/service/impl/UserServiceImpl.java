package com.pizzaserver.service.impl;

import com.pizzaserver.domain.dto.UserChangePasswordDto;
import com.pizzaserver.domain.dto.UserLoginDto;
import com.pizzaserver.domain.dto.UserLoginSuccessDto;
import com.pizzaserver.domain.dto.UserRegisterDto;
import com.pizzaserver.domain.entity.User;
import com.pizzaserver.domain.model.TokenGenerator;
import com.pizzaserver.domain.repository.UserRepository;
import com.pizzaserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service for UserApiController
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public boolean createUser(UserRegisterDto userRegisterDto) {
        //LOGGER.info("User to register: {} {}", userRegisterDto.getLogin(), userRegisterDto.getPassword());
        User userInDatabase=userRepository.findOneByLogin(userRegisterDto.getLogin());
        if(userInDatabase==null){//user does not exist - can register
            User userToAdd=new User.Builder()
                    .login(userRegisterDto.getLogin())
                    .password(userRegisterDto.getPassword())
                    .token(null)
                    .build();
            userRepository.save(userToAdd);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public UserLoginSuccessDto loginUser(UserLoginDto userLoginDto) {
        //check if user exists
        User user=userRepository.findOneByLogin(userLoginDto.getLogin());
        //try{
            if(user!=null){
                if(verifyPassword(userLoginDto.getLogin(), userLoginDto.getPassword())){
                    String token=TokenGenerator.generateNewToken();
                    user.setToken(token);
                    userRepository.save(user);
                    return new UserLoginSuccessDto.Builder().token(token).build();
                }
            }
            return null;
    }

    @Override
    public boolean verifyPassword(String login, String password) {
        User user = userRepository.verifyUser(login, password);
        if(user!=null){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public boolean checkTokenUser(String token) {
        User user = userRepository.findOneByToken(token);
        if(user!=null){//found user
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkTokenAdmin(String token) {
        User user = userRepository.findOneByToken(token);
        if(user!=null){//found user
            if(user.getLogin().equals("admin")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteUser(UserLoginDto userLoginDto) {
        User user=userRepository.findOneByLogin(userLoginDto.getLogin());
        if(user!=null){
            if(verifyPassword(userLoginDto.getLogin(), userLoginDto.getPassword())){
                userRepository.deleteByLogin(userLoginDto.getLogin());
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changePassword(UserChangePasswordDto userChangePasswordDto) {
        User user=userRepository.findOneByLogin(userChangePasswordDto.getLogin());
        if(user!=null){
            if(verifyPassword(userChangePasswordDto.getLogin(), userChangePasswordDto.getPassword())){
                userRepository.deleteByLogin(userChangePasswordDto.getLogin());
                userRepository.flush();
                user.setPassword(userChangePasswordDto.getNewPassword());
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    //for testing
    /*@Override
    public List<User> readAll() {
        List<User> users=userRepository.findAll();
        return users;
    }*/
}
