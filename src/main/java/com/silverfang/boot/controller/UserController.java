package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post")
public class UserController {
    Logger LOGGER= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    public ModelAndView createPost() {
        LOGGER.info("inside Create Controller get mapping");
        Post post = new Post();
        Category userCategory = new Category();
        ModelAndView modelAndView = new ModelAndView("addYourBlog");
        modelAndView.addObject("yourPost", post);
        modelAndView.addObject("yourCategory", userCategory);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView savePost(@ModelAttribute("yourPost") Post post, @ModelAttribute("yourCategory") Category category,@RequestParam("author") String name) {
        ModelAndView modelAndView = new ModelAndView("blogAdded");
        LOGGER.info("inside Create Controller saving blog now");
        try {
            LOGGER.info("Saving blog now");
            blogService.saveMyBlog(post, category, name);

        }
        catch (Exception e)
        {
            LOGGER.error("error occurred");
            LOGGER.error("Error while saving the blog",e);
            modelAndView.setViewName("error");
            modelAndView.addObject("msg","error while saving the blog");
        }
        return modelAndView;
    }

    @GetMapping("/view/{postId}")
    public ModelAndView viewMyPost(@PathVariable("postId") int id) {
        Post myPost = blogService.viewMyPost(id);
        ModelAndView modelAndView = new ModelAndView("displayBlog");
        modelAndView.addObject("myPost", myPost);
        return modelAndView;
    }

    @GetMapping("/edit/{postId}")
    public ModelAndView editThisPost(@PathVariable("postId") int id) {
        ModelAndView modelAndView = new ModelAndView("updateBlog");
        Post post = blogService.viewMyPost(id);
        LOGGER.info("inside edit function for post");
        Category category = new Category();
        modelAndView.addObject("myPost", post);
        modelAndView.addObject("yourCategory", category);
        return modelAndView;
    }

    @PostMapping("/edit/{postId}")
    public ModelAndView confirmEditThiPost(@ModelAttribute("myPost") Post post, @ModelAttribute("yourCategory") Category category, @RequestParam("author") String name, @PathVariable String postId) {
        System.out.println(post.getPostId());
        LOGGER.info("inside confirm edit post");
        try {
            LOGGER.info("Now saving the edited post");
            blogService.saveMyBlog(post, category, name);
        }
        catch (Exception e)
        {
            LOGGER.error("error while saving the post");
            LOGGER.error("error is ",e);
            ModelAndView modelAndView= new ModelAndView("error");
            modelAndView.addObject("msg","error while saving the post");
            return  modelAndView;
        }
        return new ModelAndView("dataSuccess");
    }

    @GetMapping("/delete/{postId}")
    public ModelAndView deleteThis(@PathVariable("postId") int id)
    {
        ModelAndView modelAndView= new ModelAndView("deleteBlog");
      Post post= blogService.viewMyPost(id);
        LOGGER.info("inside delete controller");
        modelAndView.addObject("deletePost",post);
        return  modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView deleteConfirm(@ModelAttribute("deletePost") Post deletePost)
    {
        LOGGER.info("inside confirm delete controller");
        ModelAndView modelAndView= new ModelAndView("dataDeleted");
       try {
           blogService.deleteBlog(deletePost);
       }
       catch (Exception e)
       {
           LOGGER.error("error while deleting the post");
          LOGGER.error("Error is",e);
          modelAndView.addObject("msg","error while deleting the post");
          modelAndView.setViewName("error");
          return modelAndView;
       }
        return modelAndView;
    }

}
