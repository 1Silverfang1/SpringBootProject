package com.silverfang.boot;

import com.silverfang.boot.controller.HomeController;
import com.silverfang.boot.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.MessagingException;
import java.io.IOException;
@EnableScheduling
@SpringBootApplication
public class BootblogApplication extends SpringBootServletInitializer {
    private final static Logger LOGGER= LoggerFactory.getLogger(BootblogApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BootblogApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootblogApplication.class);
    }
}
