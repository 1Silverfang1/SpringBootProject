package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
private BlogService blogService;

    @GetMapping("/inPost")
    public ModelAndView searchKey(@RequestParam("key") String key) {
        List<Post> postList = blogService.search(key);
        try{

            Category category = blogService.getCategory(key);
            System.out.println(category.getName());
            if (category != null) {
                List<Post> allPostList=blogService.filterPost(category, Pageable.unpaged());
                for(Post post:allPostList)
                {
                    if(!postList.contains(post))
                    {
                        postList.add(post);
                    }
                }
            }
        }catch (Exception e)
        {
            System.out.println("it is not a valid key");
        }
        ModelAndView modelAndView = new ModelAndView("searchedresult");
        modelAndView.addObject("allPost", postList);
        return modelAndView;
    }

}
