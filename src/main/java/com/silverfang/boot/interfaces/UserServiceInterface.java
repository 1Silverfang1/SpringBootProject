package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.UserTable;

import java.util.List;

public interface UserServiceInterface {

    List<UserTable> getUser();

    void saveUser(UserTable userTable);

    UserTable getUser(String user);

    UserTable findUserTableByEmail(String email);
    UserTable findUser(String user);
}
