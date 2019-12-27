package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private BlogService blogService;
@GetMapping("/")
    public ModelAndView getHomePage()
{
  ModelAndView modelAndView= new ModelAndView("index");
  List<Post> allPost= blogService.getMyPost();
    modelAndView.addObject("allPost",allPost);
    return  modelAndView;
}
@PostMapping("/")
public ModelAndView getHome()
{
    ModelAndView modelAndView= new ModelAndView("index");
    List<Post> allBlog= blogService.getMyPost();
    modelAndView.addObject("allPost",allBlog);
    return  modelAndView;
}

}
