package com.silverfang.boot.controller;

import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.security.AuthRequest;
import com.silverfang.boot.security.AuthResponse;
import com.silverfang.boot.security.MyUserDetailsService;
import com.silverfang.boot.security.jwt.JwtUtil;
import com.silverfang.boot.service.BlogService;
import com.silverfang.boot.service.MailService;
import com.silverfang.boot.service.helper.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthenticationController {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MailService mailService;
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
        if(userDetails.isEnabled())
        {
            final String jwtToken = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(jwtToken));
        }
        else
            return ResponseEntity.ok("Plz activate your account");
    }

    @PostMapping("/confirm-account")
    public ResponseEntity<?> confirm(@RequestBody  AuthRequest userTable) throws Exception
    {
        TokenOTP token = tokenService.findByConfirmationToken(userTable.getToken());
        Date date = new Date();
        if(token != null) {
            if (date.getTime() - token.getCreatedDate().getTime() < 360000) {

                UserTable user = userServiceInterface.getMyUser(token.getUser().getName());
                user.setEnable(true);
                userServiceInterface.saveUser(user);
                return ResponseEntity.ok("User verified");
            } else {
            return  ResponseEntity.ok("Invalid or broken link");
            }
        }
        else
            return  ResponseEntity.ok("No Token Present or token expired");
    }
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody AuthRequest user) throws Exception {

        UserTable userTable= userServiceInterface.getMyUser(user.getUserName());
        if(userTable==null)
        {
            UserTable userTable1 = userServiceInterface.getMyUserFromMail(user.getEmail());
            if (userTable1 == null) {
                userDetailsService.save(user);
                UserTable userTable2= userServiceInterface.getMyUser(user.getUserName());
                TokenOTP confirmationToken = new TokenOTP(userTable2);
                tokenService.saveToken(confirmationToken);
                SimpleMailMessage mailMessage = blogService.sendMailNow(userTable2,confirmationToken,"http://localhost:8080/confirm-account?token=");
                mailService.sendEmail(mailMessage);
                return ResponseEntity.ok("Author Registered! Check your mail for confirmation");
            }
            else
                return ResponseEntity.ok("Email already exist in the Database");
        }
        else
        {
            if(!userTable.isEnable()) {
                if (userTable.getEmail().equals(user.getEmail())) {
                    TokenOTP tokenOTP = tokenService.findByUser(userTable);
                    if (tokenOTP != null) {
                        TokenOTP tokenOTP1 = new TokenOTP(userTable);
                        tokenOTP.setCreatedDate(tokenOTP1.getCreatedDate());
                        tokenOTP.setConfirmationToken(tokenOTP1.getConfirmationToken());
                        tokenService.saveToken(tokenOTP);
                        SimpleMailMessage mailMessage = blogService.sendMailNow(userTable, tokenOTP, "http://localhost:8080/confirm-account?token=");
                        mailService.sendEmail(mailMessage);
                        return ResponseEntity.ok("Verification Link is sent to your mail id");
                    }
                    else
                    {
                        TokenOTP tokenOTP1 = new TokenOTP(userTable);
                        tokenService.saveToken(tokenOTP1);
                        SimpleMailMessage mailMessage = blogService.sendMailNow(userTable, tokenOTP1, "http://localhost:8080/confirm-account?token=");
                        mailService.sendEmail(mailMessage);
                        return ResponseEntity.ok("Verification Link is sent to your mail id");
                    }
                }
                else
                    return ResponseEntity.ok("user already exist");
            }
            else
                return ResponseEntity.ok("user already exist and is verified");
        }
    }
}
