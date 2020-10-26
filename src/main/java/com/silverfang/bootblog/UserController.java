package com.silverfang.bootblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@RequestMapping("/")
public class UserController {


    @GetMapping("/")
    public ModelAndView getView()
    {
        ModelAndView modelAndView= new ModelAndView("index");
        return  modelAndView;

    }

}
