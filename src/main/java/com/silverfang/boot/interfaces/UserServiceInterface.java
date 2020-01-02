package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.UserTable;

import java.util.List;

public interface UserServiceInterface {
    public List<UserTable> getUser();
    public void saveUser(UserTable userTable);
    public UserTable getMyUser(String user);
    public UserTable getMyUserFromMail(String email);
}
