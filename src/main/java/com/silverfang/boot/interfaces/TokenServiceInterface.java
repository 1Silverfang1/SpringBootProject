package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;

public interface TokenServiceInterface {
    TokenOTP findByConfirmationToken(String confirmationToken);
    TokenOTP findByUser(UserTable userTable);
    void saveToken(TokenOTP tokenOTP);
}
