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

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private BlogService blogService;
@GetMapping("/")
    public ModelAndView getHomePage()
{
  ModelAndView modelAndView= new ModelAndView("index");
    Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
    List<Post> allPost= blogService.getMyPost(firstPageWithTwoElements);
    modelAndView.addObject("allPost",allPost);
//    Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
//    Page<Post> postPage= (Page<Post>) blogService.getMyPost((PageRequest) firstPageWithTwoElements);
//    modelAndView.addObject("allPost",postPage);
    return  modelAndView;
}
//@GetMapping("/pageable")
//Page<Post> getmyPage()
//{
//    Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
//List<Post>page=blogService.getMyPost(firstPageWithTwoElements);
//    System.out.println(page.getTotalElements());
//    System.out.println(page.getTotalPages());
//    return page;
//}
@PostMapping("/")
public ModelAndView getHome()
{
    ModelAndView modelAndView= new ModelAndView("index");
//    List<Post> allBlog= blogService.getMyPost(PageRequest.of(0,4));
//    modelAndView.addObject("allPost",allBlog);
    return  modelAndView;
}

}
