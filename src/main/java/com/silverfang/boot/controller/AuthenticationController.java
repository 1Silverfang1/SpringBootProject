package com.silverfang.boot.controller;

import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.security.AuthRequest;
import com.silverfang.boot.security.AuthResponse;
import com.silverfang.boot.security.MyUserDetailsService;
import com.silverfang.boot.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping(value = "/authenticate")

    public ResponseEntity<?> createJwtToken(@RequestBody  AuthRequest userTable) throws Exception
    {
        try
        {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userTable.getUserName(),userTable.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            throw  new Exception("pass or user incorrect",e);
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(userTable.getUserName());
        final  String jwtToken=jwtUtil.generateToken(userDetails);

//    return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        return ResponseEntity.ok(new AuthResponse(jwtToken));

    }
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody AuthRequest user) throws Exception {
        userDetailsService.save(user);
        return ResponseEntity.ok("Author Registered");
    }
}
