package com.silverfang.boot.repository;

import com.silverfang.boot.model.TokenOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenOTP,Integer> {
    TokenOTP findByConfirmationToken(String confirmationToken);
}
