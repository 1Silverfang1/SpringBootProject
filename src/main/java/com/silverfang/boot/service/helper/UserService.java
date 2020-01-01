package com.silverfang.boot.service.helper;

import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.UserRepository;
import com.silverfang.boot.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserServiceInterface {
    @Autowired
private UserRepository userRepository;


    @Override
    public List<UserTable> getUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserTable userTable) {
        userRepository.save(userTable);
    }

    @Override
    public UserTable getUser(String user) {
        return userRepository.findUserTableByNameContaining(user);
    }
}
