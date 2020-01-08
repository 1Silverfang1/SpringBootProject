package com.silverfang.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BootBlogApplication extends SpringBootServletInitializer {
    private final static Logger LOGGER= LoggerFactory.getLogger(BootBlogApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BootBlogApplication.class, args);
        LOGGER.info("Starting main Boot function");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootBlogApplication.class);
    }
}
