package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private BlogService blogService;
    Logger logger = LoggerFactory.getLogger(HomeController.class);

@GetMapping("/")
    public ModelAndView getHomePage()
{
    logger.debug("hey");
    logger.info("afesfwascx" +
            "");
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

@GetMapping("/sortby/{title}/{page}")
    public ModelAndView sortHomePageBytitle(@PathVariable("title") String title,@PathVariable("page") int page)
{
    ModelAndView modelAndView = new ModelAndView("index");
    Pageable paging = PageRequest.of(page, 4,Sort.by(title));
    if(title.equals("updatedAt"))
    paging=PageRequest.of(page,4,Sort.by(title).descending());
    List<Post> pagenationPost= blogService.getMyPost(paging);
   List<Post>  allPost= blogService.getMyPost(Pageable.unpaged());
    int total=allPost.size()/4;
    modelAndView.addObject("CurPage",page);
    modelAndView.addObject("totalPage",total);
    modelAndView.addObject("allPost",pagenationPost);
    return  modelAndView;
}
    @GetMapping({"/sortby/{title}/filterby/{filter}/{pageid}","filterby/{filter}/sortby/{title}/{pageid}"})
    public ModelAndView sortHomePageBytitleAndSort(@PathVariable("filter") String filter,@PathVariable("title") String title,@PathVariable("pageid") int page)
    {
        System.out.println("ascdvsvfv");
        ModelAndView modelAndView = new ModelAndView("index");
        Category category= blogService.getSingleCategory(filter);
        Pageable paging = PageRequest.of(page, 4);
        List<Post> filteredPost= new ArrayList<>();
        List<Post> allPostList= new ArrayList<>();
        if(title.equals("title")) {
            filteredPost = blogService.filterandSortbytitle(category, paging);
            allPostList = blogService.filterandSortbytitle(category, Pageable.unpaged());
        }
        else if(title.equals("content"))
        {
            filteredPost= blogService.filterandSortbyContent(category,paging);
            allPostList = blogService.filterandSortbyContent(category, Pageable.unpaged());
        }
        else if(title.equals("updatedAt"))
        {
            filteredPost= blogService.filterandSortbyUpdate(category,paging);
            allPostList = blogService.filterandSortbyUpdate(category, Pageable.unpaged());
        }
        else if (title.equals("createdAt"))
        {
            filteredPost= blogService.filterandSortbyCreation(category,paging);
            allPostList = blogService.filterandSortbyCreation(category, Pageable.unpaged());
        }
        allPostList=blogService.filterPost(category, Pageable.unpaged());
        modelAndView.addObject("CurPage",page);
        int size=allPostList.size()/4;
        modelAndView.addObject("totalPage",size);
        modelAndView.addObject("allPost",filteredPost);
return  modelAndView;
    }

    @GetMapping("/filterby/{filter}/{pageid}")
    public ModelAndView getAllHorrorPost(@PathVariable("filter") String filter,@PathVariable("pageid")int id)
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Category category= blogService.getSingleCategory(filter);
        Pageable paging = PageRequest.of(id, 4);
        List<Post> filteredPost= blogService.filterPost(category,paging);
        List<Post> allPostList=blogService.filterPost(category, Pageable.unpaged());
        modelAndView.addObject("CurPage",id);
        int size=allPostList.size()/4;
        modelAndView.addObject("totalPage",size);
        modelAndView.addObject("allPost",filteredPost);
        return modelAndView;
    }
}
