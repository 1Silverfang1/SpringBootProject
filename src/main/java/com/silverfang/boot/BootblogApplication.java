package com.silverfang.boot;

import com.silverfang.boot.controller.HomeController;
import com.silverfang.boot.model.Post;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.MessagingException;
import java.io.IOException;
@EnableScheduling
@SpringBootApplication
public class BootblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootblogApplication.class, args);
    }

}
