package com.silverfang.boot.security;

import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String User) throws UsernameNotFoundException {

        UserTable userTable= userRepository.findUserTableByNameContaining(User);
        if(userTable==null)
        {
            throw new UsernameNotFoundException("user not in the database" + User);
        }

        return new MyUserDetail(userTable.getName(),userTable.getPassword(),userTable.getRoles(),userTable.isEnable());
    }
    public void save(AuthRequest user) {
        UserTable newUser = new UserTable();
        newUser.setName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setRoles("Author");
        newUser.setEnable(false);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
    }
}
