package com.silverfang.boot.service;

import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private PostServiceInterface postServiceInterface;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private CategoryServiceInterface categoryServiceInterface;
    public String saveMyBlog(Post myPost, Category myCategory)
    {
        postServiceInterface.savePost(myPost);
        Post post= postServiceInterface.getPostById(myPost.getPostId());
        String[] cat= myCategory.getName().split(",");
            for(String category:cat) {
                Category category1 = new Category(category);
                category1.getCategoryPost().add(post);
                categoryServiceInterface.saveCategory(category1);
                post.getListCategory().add(category1);
            }
          postServiceInterface.savePost(post);
        return "blog Saved";

    }
}
