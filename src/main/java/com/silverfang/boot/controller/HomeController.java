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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
//    @GetMapping("/{PageNumber}")
//    public ModelAndView getHomePage(@PathVariable("PageNumber") int page)
//    {
//        ModelAndView modelAndView= new ModelAndView("index");
//        Pageable paging = PageRequest.of(page, 4);
//        List<Post> postList=blogService.getMyPost();
//        int i=postList.size();

//        System.out.println(i+" "+i/4);
//        List<Post> allPost= blogService.getMyPost(paging);
//        modelAndView.addObject("allPost",allPost);
//        modelAndView.addObject("CurPage",page);
//        modelAndView.addObject("totalPage",i/4);
//        return  modelAndView;
//    }
@GetMapping("/post")
    public ModelAndView sortHomePageByTitle(@RequestParam(defaultValue = "title",required = false, name = "sortBy") String title,
                                            @RequestParam(defaultValue = "0" ,required = false,name = "page") int page ,
                                            @RequestParam(defaultValue = "" ,required = false, name = "filterBy") String filter,
                                            @RequestParam(defaultValue = "", required = false ,name = "key")String key) {
    ModelAndView modelAndView = new ModelAndView("index");
    if (!key.equals(""))
    {
        List<Post> postList=blogService.searchMyBlog(key);
        List<Post> postList2= new ArrayList<>();
        if(!filter.equals(""))
        {
            Category category= blogService.getSingleCategory(filter);
            System.out.println(category.getName());
            List<Post> postList1= blogService.filterPost(category,Pageable.unpaged());
            System.out.println(postList1.size());
        for(Post post:postList)
        {
            System.out.println(post.getListCategory().get(0).getName());
            if(postList1.contains(post))
            {
                postList2.add(post);
            }
        }
        }
        if(postList2.size()==0)
            postList2=postList;

        ModelAndView modelAndView1 = new ModelAndView("searchedresult");
        modelAndView1.addObject("allPost", postList2);
        return modelAndView1;
    }
    if(!filter.equals(""))
    {
        System.out.println("sadasd");
        Category category= blogService.getSingleCategory(filter);
        Pageable pageable= PageRequest.of(page,4,Sort.by(title));
        if(title.equals("updatedAt"))
            pageable= PageRequest.of(page,4,Sort.by(title).descending());
        List<Post> postList= blogService.filterPost(category,pageable);
        List<Post> list=blogService.filterPost(category,Pageable.unpaged());
        modelAndView.addObject("allPost",postList);
        int total=list.size()/4;
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",total);
        return  modelAndView;
    }
    Pageable paging;
    if(title.equals("updatedAt")) {
        paging = PageRequest.of(page, 4, Sort.by(title).descending());
    }
    paging = PageRequest.of(page, 4,Sort.by(title));
    System.out.println(title);
    List<Post> pagenationPost= blogService.getMyPost(paging);
   List<Post>  allPost= blogService.getMyPost(Pageable.unpaged());
    int total=allPost.size()/4;
    modelAndView.addObject("CurPage",page);
    modelAndView.addObject("totalPage",total);
    modelAndView.addObject("allPost",pagenationPost);
    return  modelAndView;
}
}
