package com.pizzaserver.domain.dto;

import com.pizzaserver.domain.entity.User;

import java.util.List;

/**
 * Class for testing - stores entire database in ArrayList
 * <p>
 * Not used by default
 */
public class UserListDto {

    private List<User> userList;

    public UserListDto(Builder builder) {
        this.userList = builder.userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public static final class Builder {
        private List<User> userList;

        public Builder() {
        }

        public static Builder anUserListDto() {
            return new Builder();
        }

        public Builder userList(List<User> userList) {
            this.userList = userList;
            return this;
        }

        public UserListDto build() {
            return new UserListDto(this);
        }
    }
}
