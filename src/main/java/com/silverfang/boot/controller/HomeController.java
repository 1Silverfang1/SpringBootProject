package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private BlogService blogService;
@GetMapping("/")
    public ModelAndView getHomePage()
{
  ModelAndView modelAndView= new ModelAndView("index");
    Pageable paging = PageRequest.of(0, 4);
    List<Post> allPost= blogService.getMyPost(paging);
    modelAndView.addObject("allPost",allPost);
    return  modelAndView;
}

@GetMapping("/sort-by-title")
    public ModelAndView sortHomePageBytitle()
{
    ModelAndView modelAndView = new ModelAndView("index");
    Pageable paging = PageRequest.of(0, 4,Sort.by("title"));
    List<Post> allPost= blogService.getMyPost(paging);
    modelAndView.addObject("allPost",allPost);
    return  modelAndView;
}
    @GetMapping("/sort-by-content")
    public ModelAndView sortHomePageById()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        Pageable paging = PageRequest.of(0, 4,Sort.by("content"));
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        return  modelAndView;
    }
    @GetMapping("/sort-by-creation")
    public ModelAndView sortHomePageByCreation()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        Pageable paging = PageRequest.of(0, 4,Sort.by("createdAt").descending());
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        return  modelAndView;
    }
    @GetMapping("/sort-by-updated")
    public ModelAndView sortHomePageByUpdated()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        Pageable paging = PageRequest.of(0, 4,Sort.by("updatedAt").descending());
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        return  modelAndView;
    }

}
