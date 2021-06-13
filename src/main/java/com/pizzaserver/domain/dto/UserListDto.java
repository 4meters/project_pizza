package com.pizzaserver.domain.dto;

import com.pizzaserver.domain.entity.User;

import java.util.List;

public class UserListDto {
    private List<User> userList;

    public UserListDto(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
