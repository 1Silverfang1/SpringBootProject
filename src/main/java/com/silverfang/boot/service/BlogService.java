package com.silverfang.boot.service;

import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private PostServiceInterface postServiceInterface;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private CategoryServiceInterface categoryServiceInterface;

    public String saveMyBlog(Post myPost, Category myCategory) {
        if(myCategory.getName()!=null) {
            String[] cat = myCategory.getName().split(",");
            for (String category : cat) {
                Category category1= categoryServiceInterface.getsingleCategory(category);
                myPost.getListCategory().add(category1);
            }
        }
        postServiceInterface.savePost(myPost);
        return "Blog saved";
    }

    public Post viewMyPost(int id)
    {
        return postServiceInterface.getPostById(id);
    }

    public List<Post> getMyPost()
    {
        return postServiceInterface.getPost();

    }
    public List<Post> getMyPost(Pageable pageRequest)
    {
        return postServiceInterface.getPost(pageRequest);
    }
public void deleteBlog(Post post)
{
    postServiceInterface.deleteBlog(post);
}
public List<Post> filterPost(Category filter)
{
    return postServiceInterface.filterPost(filter);
}
}
