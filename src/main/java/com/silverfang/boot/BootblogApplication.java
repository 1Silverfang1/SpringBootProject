package com.silverfang.boot;

import com.silverfang.boot.controller.HomeController;
import com.silverfang.boot.model.Post;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootblogApplication.class, args);

    }
}
