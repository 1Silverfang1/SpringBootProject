package com.silverfang.boot.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyUserDetail implements UserDetails {

    private  String username;
    private  String password;
    private List<GrantedAuthority> authorities;
    private Boolean enable;

    MyUserDetail(String username, String password, String role, Boolean enable) {
        this.username = username;
        this.password = password;
        this.enable=enable;
        if(role.equals("AUTHOR"))
        {
            authorities= Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else
        {
            authorities= Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }

    public MyUserDetail() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}