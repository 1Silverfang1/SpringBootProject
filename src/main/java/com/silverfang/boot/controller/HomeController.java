package com.silverfang.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    Logger LOGGER= LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/chat")
    public ModelAndView getMessage()
    {
        return new ModelAndView("ChatBot");
    }
}
