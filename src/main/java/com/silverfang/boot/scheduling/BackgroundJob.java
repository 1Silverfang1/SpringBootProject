package com.silverfang.boot.scheduling;


import com.silverfang.boot.controller.HomeController;
import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BackgroundJob {
    Logger LOGGER= LoggerFactory.getLogger(BackgroundJob.class);
    @Autowired
    private TokenRepository tokenRepository;
        @Scheduled(fixedRate = 500000)
    public void deleteOTP()
        {
            LOGGER.info("Starting background task for deleting expired token form database");
            Date date = new Date();
           List<TokenOTP> tokenOTPS = tokenRepository.findAll();
           try {
               for (TokenOTP tokenOTP : tokenOTPS) {
                   if (date.getTime() - tokenOTP.getCreatedDate().getTime() > 600000) {
                       LOGGER.warn("Deleting token from database");
                       tokenRepository.delete(tokenOTP);
                       LOGGER.warn("token deleted for : " + tokenOTP.getUser().getName());
                   }
               }
           }
           catch (Exception e)
           {
               LOGGER.error("error while deleting the expired token",e);
           }
           LOGGER.info("Stopping background task till next scheduled run");
    }
}
