package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/post")
public class UserController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    public ModelAndView createPost() {
        Post post = new Post();
        Category userCategory = new Category();
        ModelAndView modelAndView = new ModelAndView("addYourBlog");
        modelAndView.addObject("yourPost", post);
        modelAndView.addObject("yourCategory", userCategory);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView savePost(@ModelAttribute("yourPost") Post post, @ModelAttribute("yourCategory") Category category) {
        ModelAndView modelAndView = new ModelAndView("blogAdded");
        blogService.saveMyBlog(post, category);
        return modelAndView;
    }

    @GetMapping("/view/{postId}")
    public ModelAndView viewMyPost(@PathVariable("postId") int id) {
        Post myPost = blogService.viewMyPost(id);
        ModelAndView modelAndView = new ModelAndView("DisplayBlog");
        modelAndView.addObject("myPost", myPost);
        return modelAndView;
    }

    @GetMapping("/edit/{postId}")
    public ModelAndView editThisPost(@PathVariable("postId") int id) {
        ModelAndView modelAndView = new ModelAndView("UpdateBlog");
        Post post = blogService.viewMyPost(id);
        Category category = new Category();
        modelAndView.addObject("myPost", post);
        modelAndView.addObject("yourCategory", category);
        return modelAndView;
    }

    @PostMapping("/edit/{postId}")
    public ModelAndView confirmEditThiPost(@ModelAttribute("myPost") Post post, @ModelAttribute("yourCategory") Category category) {
        System.out.println(post.getPostId());
        blogService.saveMyBlog(post, category);
        ModelAndView modelAndView = new ModelAndView("DataSucess");
        return modelAndView;
    }

    @GetMapping("/delete/{postId}")
    public ModelAndView deleteThis(@PathVariable("postId") int id)
    {
        ModelAndView modelAndView= new ModelAndView("DeleteBlog");
      Post post= blogService.viewMyPost(id);
        modelAndView.addObject("deletePost",post);
        return  modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView deleteConfirm(@ModelAttribute("deletePost") Post deletePost)
    {
        ModelAndView modelAndView= new ModelAndView("dataDeleted");
        blogService.deleteBlog(deletePost);
        return modelAndView;
    }

}
