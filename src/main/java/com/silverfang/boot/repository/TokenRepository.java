package com.silverfang.boot.repository;

import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<TokenOTP,Integer> {
    TokenOTP findByConfirmationToken(String confirmationToken);
    TokenOTP findByUser(UserTable userTable);
}
