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

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class BootblogApplication implements CommandLineRunner {
    @Autowired
    private JavaMailSender javaMailSender;
    public static void main(String[] args) {
        SpringApplication.run(BootblogApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending Email...");
        sendEmail();
        System.out.println("Done");

    }
    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("imrawatsvaibhav@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
}
