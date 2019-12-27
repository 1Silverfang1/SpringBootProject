package com.silverfang.boot.service;

import com.silverfang.boot.dao.CategoryServiceInterface;
import com.silverfang.boot.dao.PostServiceInterface;
import com.silverfang.boot.dao.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
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
        String[] cat = myCategory.getName().split(",");
        for (String category : cat) {
            Category category1 = new Category(category);
            category1.getCategoryPost().add(myPost);
            myPost.getListCategory().add(category1);
        }
        postServiceInterface.savePost(myPost);
        return "blog Saved";

    }

    public Post viewMyPost(int id)
    {
        return postServiceInterface.getPostById(id);
    }

    public List<Post> getMyPost()
    {
        return postServiceInterface.getPost();

    }
    public void editMyBlog(Post myPost,Category myCategory)
    {
     postServiceInterface.savePost(myPost);
//
//        List<Category> categoryList= categoryServiceInterface.getCategory();
//        List<Post> postList= new ArrayList<>();
//        for( Category category:categoryList)
//        {
//            postList= category.getCategoryPost();
//            category.getCategoryPost().clear();
//            for(Post post:postList)
//            {
//                if(post.getPostId()==myPost.getPostId())
//                {
//                    postList.remove(post);
//                }
//            }
//            category.setCategoryPost(postList);
//        categoryServiceInterface.saveCategory(category);
//        }
        saveMyBlog(myPost, myCategory);

    }
public void deleteBlog(Post post)
{
    postServiceInterface.deleteBlog(post);
}
}
