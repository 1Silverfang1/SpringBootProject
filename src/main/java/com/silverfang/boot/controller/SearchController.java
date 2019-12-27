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

        List<Post> postList=blogService.searchMyBlog(key);
        ModelAndView modelAndView = new ModelAndView("searchedresult");
        modelAndView.addObject("allPost", postList);
        return modelAndView;
    }

}
