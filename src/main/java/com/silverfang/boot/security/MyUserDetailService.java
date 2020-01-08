package com.silverfang.boot.security;

import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserServiceInterface userServiceInterface;
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        UserTable userTable = userServiceInterface.findUser(user);
        if(userTable==null)
        {
            throw  new UsernameNotFoundException("User not found");

        }
        return new MyUserDetails(userTable.getName(),userTable.getPassword(),userTable.getRoles(),userTable.isEnable());
    }
    public void save(UserTable user)
     {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userServiceInterface.saveUser(user);
    }
}
