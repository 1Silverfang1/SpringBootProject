package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private PostServiceInterface service;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private CategoryServiceInterface categoryServiceInterface;
@GetMapping("/")
    public ModelAndView getHomePage()
{
    UserTable userTable = new UserTable("ASF","Ew","WFe");
    Category category= new Category("mycat1");
    Category category1= new Category("mycat2");
    categoryServiceInterface.saveCategory(category);
    categoryServiceInterface.saveCategory(category1);
    userServiceInterface.saveUser(userTable);
    Post post= new Post("This is ","new page");
    post.setUserTable(userTable);
    post.getListCategory().add(category);
    post.getListCategory().add(category1);
    service.savePost(post);
    Post post1= new Post("afsd","Afe");
    post1.setUserTable(userTable);
    post1.getListCategory().add(category);
    service.savePost(post1);
    Post post2= new Post("SDasdcaV","S");
    post2.setUserTable(userTable);
    post2.getListCategory().add(category);
    service.savePost(post2);
    ModelAndView modelAndView= new ModelAndView("index");
    userTable.getPostList().add(post);
    userTable.getPostList().add(post1);
    userTable.getPostList().add(post2);
    userServiceInterface.saveUser(userTable);
    service.savePost(post);
    List<Post> allBlog= service.getPost();
    System.out.println(allBlog);
    List<UserTable> allUserTable =userServiceInterface.getUser();
    System.out.println(allUserTable);
    modelAndView.addObject("allPost",allBlog);
    modelAndView.addObject("allUser", allUserTable);
    return  modelAndView;
}
@PostMapping("/")
public ModelAndView getHome()
{
    ModelAndView modelAndView= new ModelAndView("index");
    List<Post> allBlog= service.getPost();
    modelAndView.addObject("allPost",allBlog);
    return  modelAndView;
}

}
