package com.ccf.logic.restclient.webservice.user;

import com.ccf.logic.login.User;

public class UserEntityMapper {

    public User transform(UserEntity userEntity) {
        if (userEntity == null)
            return null;
        else
            return new User(userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getLastName(),
                    userEntity.getLogin(),
                    userEntity.getPassword());
    }
}
