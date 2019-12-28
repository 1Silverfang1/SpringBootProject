package com.silverfang.boot.service;

import com.silverfang.boot.interfaces.CategoryServiceInterface;
import com.silverfang.boot.interfaces.PostServiceInterface;
import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private PostServiceInterface postServiceInterface;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private CategoryServiceInterface categoryServiceInterface;
    public Category getSingleCategory(String cateGoryName)
    {
        return categoryServiceInterface.getsingleCategory(cateGoryName);
    }
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
    public List<Post> filterandSortbyContent(Category filter,Pageable pageable)
    {
        return postServiceInterface.filterPostByContent(filter, pageable);
    }
    public List<Post> filterandSortbytitle(Category filter,Pageable pageable)
    {
        return postServiceInterface.filterPostByTitle(filter, pageable);
    }
    public List<Post> filterandSortbyCreation(Category filter,Pageable pageable)
    {
        return postServiceInterface.filterPostCreatedAt(filter, pageable);
    }
    public List<Post> filterandSortbyUpdate(Category filter,Pageable pageable)
    {
        return postServiceInterface.filterPostUpdatedAt(filter, pageable);
    }
public void deleteBlog(Post post)
{
    postServiceInterface.deleteBlog(post);
}
public List<Post> filterPost(Category filter,Pageable pageable)
{
    return postServiceInterface.filterPost(filter,pageable);
}

public List<Post> search(String key)
{
    return postServiceInterface.searchInPost(key);
}
    public Category getCategory(String key) {
   return  categoryServiceInterface.getCategory(key);
    }
public List<Post> searchMyBlog(String key)
{
    List<Post> postList = search(key);
    try{

        Category category = getCategory(key);
        System.out.println(category.getName());
        List<Post> allPostList=filterPost(category, Pageable.unpaged());
        for(Post post:allPostList)
        {
            if(!postList.contains(post))
            {
                postList.add(post);
            }
        }
    }catch (Exception e)
    {
        System.out.println("it is not a valid key");
    }
    return postList;
}
}
