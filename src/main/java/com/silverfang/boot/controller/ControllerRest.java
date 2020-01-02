package com.silverfang.boot.controller;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class ControllerRest {
    @Autowired
    private BlogService blogService;
    @GetMapping(headers = "Accept=application/json",value = "/posts",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Post>> getMyPosts(@RequestParam(defaultValue = "title",required = false, name = "sortBy") String title,
                                                      @RequestParam(defaultValue = "0" ,required = false,name = "page") int page ,
                                                      @RequestParam(defaultValue = "" ,required = false, name = "filterBy") String filter,
                                                      @RequestParam(defaultValue = "", required = false ,name = "key")String key,
                                                      @RequestParam(defaultValue = "4",required = false,name = "pageSize")int pageSize )
    {
        if (!key.equals(""))
        {
            Pageable pageable= PageRequest.of(page,pageSize, Sort.by(title));
            ArrayList<Post> postList= (ArrayList<Post>) blogService.searchMyBlog(key,pageable);
            ArrayList<Post> postList2= new ArrayList<>();
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
                return new ResponseEntity<>(postList2, HttpStatus.OK);
            }
            return new ResponseEntity<>(postList, HttpStatus.OK);
        }
        if(!filter.equals(""))
        {
            System.out.println("sadasd");
            Category category= blogService.getSingleCategory(filter);
            Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
            if(title.equals("updatedAt"))
                pageable= PageRequest.of(page,pageSize,Sort.by(title).descending());
            ArrayList<Post> postList= (ArrayList<Post>)blogService.filterPost(category,pageable);
            return new ResponseEntity<>(postList, HttpStatus.OK);
        }
        Pageable paging;
        paging = PageRequest.of(page, pageSize,Sort.by(title));
        if(title.equals("updatedAt")) {
            paging = PageRequest.of(page, pageSize, Sort.by(title).descending());
        }
        ArrayList<Post> pagenationPost= (ArrayList<Post>) blogService.getMyPost(paging);
        return new ResponseEntity<>(pagenationPost, HttpStatus.OK);
    }
    @GetMapping(headers = "Accept=application/json",value = "/posts/{postId}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getMyPost(@PathVariable("postId") int postId)
    {

        Post posts= blogService.viewMyPost(postId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(headers = "Accept=application/json",value = "/posts",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> saveMyPost(@RequestParam(name = "title") String title,
                                           @RequestParam(name = "content") String content,@RequestParam(name = "category",required = false,defaultValue = "") String category)
    {
        Post post= new Post(title,content);
        Category category1= new Category(category);
        String result=blogService.saveMyBlog(post,category1);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @PutMapping(headers = "Accept=application/json",value = "/posts/{postId}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> editMyPost(@PathVariable("postId") int postId,@RequestParam(name = "title") String title,
                                           @RequestParam(name = "content") String content,@RequestParam(name = "category",required = false,defaultValue = "") String category)
    {
        Post post = blogService.viewMyPost(postId);
        post.setTitle(title);
        post.setContent(content);
        post.getListCategory().clear();
        Category category1= new Category();
        if(!category.equals("")) {
            category1.setName(category);
        }
        blogService.saveMyBlog(post,category1);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @DeleteMapping(headers = "Accept=application/json",value = "/posts/{postId}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> deleteMyPost(@PathVariable("postId") int postId)
    {   
        Post post = blogService.viewMyPost(postId);

        blogService.deleteBlog(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


}
