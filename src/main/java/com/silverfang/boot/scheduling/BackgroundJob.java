package com.silverfang.boot.scheduling;


import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BackgroundJob {
    @Autowired
    private TokenRepository tokenRepository;
        @Scheduled(fixedRate = 500000)
    public void deleteOTP()
        {
            Date date = new Date();
           List<TokenOTP> tokenOTPS = tokenRepository.findAll();
           for(TokenOTP tokenOTP:tokenOTPS)
           {
               if(date.getTime()-tokenOTP.getCreatedDate().getTime()>400000)
               {
                   tokenRepository.delete(tokenOTP);
                   System.out.println("deleting token for :"+ tokenOTP.getUser().getName());
               }
           }
    }
}
