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
import org.springframework.web.bind.annotation.PathVariable;
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
    private CategoryServiceInterface categoryServiceInterface;
    @Autowired
    private BlogService blogService;
@GetMapping("/")
    public ModelAndView getHomePage()
{
  ModelAndView modelAndView= new ModelAndView("index");
    Pageable paging = PageRequest.of(0, 4);
    List<Post> postList=blogService.getMyPost();
    int i=postList.size();
    System.out.println(i+" "+i/4);
    List<Post> allPost= blogService.getMyPost(paging);
    modelAndView.addObject("allPost",allPost);
    modelAndView.addObject("CurPage",0);
    modelAndView.addObject("totalPage",i/4);
    return  modelAndView;
}
    @GetMapping("/{PageNumber}")
    public ModelAndView getHomePage(@PathVariable("PageNumber") int page)
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Pageable paging = PageRequest.of(page, 4);
        List<Post> postList=blogService.getMyPost();
        int i=postList.size();
        System.out.println(i+" "+i/4);
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",i/4);
        return  modelAndView;
    }

@GetMapping("/sort-by-title/{page}")
    public ModelAndView sortHomePageBytitle(@PathVariable("page") int page)
{
    ModelAndView modelAndView = new ModelAndView("index");
    Pageable paging = PageRequest.of(page, 4,Sort.by("title"));
    List<Post> allPost= blogService.getMyPost(paging);
    List<Post> postList=blogService.getMyPost();
    int i=postList.size();
    modelAndView.addObject("CurPage",page);
    modelAndView.addObject("totalPage",i/4);
    modelAndView.addObject("allPost",allPost);

    return  modelAndView;
}
    @GetMapping("/sort-by-content/{page}")
    public ModelAndView sortHomePageById(@PathVariable("page") int page)
    {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Post> postList=blogService.getMyPost();
        int i=postList.size();
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",i/4);
        Pageable paging = PageRequest.of(page, 4,Sort.by("content"));
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        return  modelAndView;
    }
    @GetMapping("/sort-by-creation/{page}")
    public ModelAndView sortHomePageByCreation(@PathVariable("page") int page)
    {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Post> postList=blogService.getMyPost();
        int i=postList.size();
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",i/4);
        Pageable paging = PageRequest.of(page, 4,Sort.by("createdAt").descending());
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        return  modelAndView;
    }
    @GetMapping("/sort-by-updated/{page}")
    public ModelAndView sortHomePageByUpdated(@PathVariable("page") int page)
    {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Post> postList=blogService.getMyPost();
        int i=postList.size();
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",i/4);
        Pageable paging = PageRequest.of(page, 4,Sort.by("updatedAt").descending());
        List<Post> allPost= blogService.getMyPost(paging);
        modelAndView.addObject("allPost",allPost);
        return  modelAndView;
    }

    @GetMapping("/filterby/horror")
    public ModelAndView getAllHorrorPost()
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Category category= categoryServiceInterface.getsingleCategory("horror");
        List<Post> filteredPost= blogService.filterPost(category);
        modelAndView.addObject("CurPage",0);
        modelAndView.addObject("totalPage",1);
        modelAndView.addObject("allPost",filteredPost);
        System.out.println(filteredPost.size());
        return modelAndView;
    }
    @GetMapping("/filterby/SCI-FI")
    public ModelAndView getAllSciencePost()
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Category category= categoryServiceInterface.getsingleCategory("SCI-FI");
        List<Post> filteredPost= blogService.filterPost(category);
        modelAndView.addObject("CurPage",0);
        modelAndView.addObject("totalPage",1);
        modelAndView.addObject("allPost",filteredPost);
        System.out.println(filteredPost.size());
        return modelAndView;
    }
    @GetMapping("/filterby/Romance")
    public ModelAndView getAllRomancePost()
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Category category= categoryServiceInterface.getsingleCategory("Romance");
        List<Post> filteredPost= blogService.filterPost(category);
        modelAndView.addObject("CurPage",0);
        modelAndView.addObject("totalPage",1);
        modelAndView.addObject("allPost",filteredPost);
        System.out.println(filteredPost.size());
        return modelAndView;
    }
    @GetMapping("/filterby/Comic")
    public ModelAndView getAllComicPost()
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Category category= categoryServiceInterface.getsingleCategory("Comic");
        List<Post> filteredPost= blogService.filterPost(category);
        modelAndView.addObject("CurPage",0);
        modelAndView.addObject("totalPage",1);
        modelAndView.addObject("allPost",filteredPost);
        System.out.println(filteredPost.size());
        return modelAndView;
    }

}
