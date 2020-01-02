package com.silverfang.boot.service.helper;

import com.silverfang.boot.interfaces.TokenServiceInterface;
import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements TokenServiceInterface {
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public TokenOTP findByConfirmationToken(String confirmationToken) {
        return tokenRepository.findByConfirmationToken(confirmationToken);
    }

    @Override
    public TokenOTP findByUser(UserTable userTable) {
        return tokenRepository.findByUser(userTable);
    }

    @Override
    public void saveToken(TokenOTP tokenOTP) {
        tokenRepository.save(tokenOTP);
    }
}
