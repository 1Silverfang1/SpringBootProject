package com.silverfang.boot.service.helper;

import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.UserRepository;
import com.silverfang.boot.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
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

    @Override
    public UserTable findUserTableByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserTable findUser(String user) {
        return userRepository.findUserTableByName(user);
    }
}
