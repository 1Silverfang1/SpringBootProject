package com.silverfang.bootblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@RequestMapping("/")
public class UserController {
    @Autowired
    private ServiceInterface serviceInterface;
    @GetMapping("/home")
    public List<Post> getAllBlogs()
    {
        return serviceInterface.getPost();
    }
    @GetMapping("/")
    public ModelAndView jhj()
    {
        System.out.println("se;dfkjhjkdl");
        ModelAndView modelAndView= new ModelAndView("index");
        Post post= new Post(1,"Af","Afe","AEF","AEF","AEF","AEF");
        serviceInterface.savePost(post);
        List<Post> allBlog= serviceInterface.getPost();
        modelAndView.addObject("result",allBlog);
        return  modelAndView;

    }

}
