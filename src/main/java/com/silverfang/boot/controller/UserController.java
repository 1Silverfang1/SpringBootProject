package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/post")
public class UserController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/create")
    public ModelAndView createPost()
    {
        Post post= new Post();
        Category userCategory= new Category();
        ModelAndView modelAndView= new ModelAndView("addYourBlog");
        modelAndView.addObject("yourPost",post);
        modelAndView.addObject("yourCategory",userCategory);
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView savePost(@ModelAttribute("yourPost") Post post,@ModelAttribute("yourCategory") Category category)
    {
        ModelAndView modelAndView= new ModelAndView("blogAdded");
        blogService.saveMyBlog(post,category);
//        System.out.println(post.getContent()+post.getTitle());
//        System.out.println(category.getName());

        return  modelAndView;
    }

}
