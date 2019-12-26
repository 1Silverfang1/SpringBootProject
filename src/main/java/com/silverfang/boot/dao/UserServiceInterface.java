package com.silverfang.boot.dao;

import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.model.UserTable;

import java.util.List;

public interface UserServiceInterface {
    public List<UserTable> getUser();
    public void saveUser(UserTable userTable);
}
