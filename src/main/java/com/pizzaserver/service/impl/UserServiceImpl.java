package com.pizzaserver.service.impl;

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

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public String readUser(String login) {
        User user=userRepository.findOneByLogin(login);
        //if(user!=null)
        return user.getLogin();
    }
    @Override
    public List<User> readAll() {
        List<User> users=userRepository.findAll();
        return users;
    }

    @Override
    public boolean createUser(UserRegisterDto userRegisterDto) {//?mapper na User?
        //LOGGER.info("User to register: {} {}", userRegisterDto.getLogin(), userRegisterDto.getPassword());
        User userInDatabase=userRepository.findOneByLogin(userRegisterDto.getLogin());
        if(userInDatabase==null){//uzytkownik nie istnieje w bazie - mozna rejestrowac
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
        if(user!=null){
            if(user.getPassword().equals(userLoginDto.getPassword())){
                String token=TokenGenerator.generateNewToken();
                user.setToken(token);
                userRepository.save(user);
                return new UserLoginSuccessDto(token);
            }
        }
        return null;
    }

    @Override
    public boolean checkTokenUser(String token) {
        User user = userRepository.findOneByToken(token);
        if(user!=null){//znaleziono
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkTokenAdmin(String token) {
        User user = userRepository.findOneByToken(token);
        if(user!=null){//znaleziono
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
}
