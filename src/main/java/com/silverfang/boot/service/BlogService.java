package com.silverfang.boot.service;

import com.silverfang.boot.dao.CategoryServiceDao;
import com.silverfang.boot.dao.PostServiceDao;
import com.silverfang.boot.dao.UserServiceDao;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private PostServiceDao postServiceDao;
    @Autowired
    private UserServiceDao userServiceDao;
    @Autowired
    private CategoryServiceDao categoryServiceDao;
    public Category getSingleCategory(String cateGoryName)
    {
        return categoryServiceDao.getsingleCategory(cateGoryName);
    }
    public String saveMyBlog(Post myPost, Category myCategory) {
        if(myCategory.getName()!=null) {
            String[] cat = myCategory.getName().split(",");
            for (String category : cat) {
                Category category1= categoryServiceDao.getsingleCategory(category);
                myPost.getListCategory().add(category1);
            }
        }
        postServiceDao.savePost(myPost);
        return "Blog saved";
    }

    public Post viewMyPost(int id)
    {
        return postServiceDao.getPostById(id);
    }

    public List<Post> getMyPost()
    {
        return postServiceDao.getPost();

    }
    public List<Post> getMyPost(Pageable pageRequest)
    {
        return postServiceDao.getPost(pageRequest);
    }
public void deleteBlog(Post post)
{
    postServiceDao.deleteBlog(post);
}
public List<Post> filterPost(Category filter,Pageable pageable)
{
    return postServiceDao.filterPost(filter,pageable);
}
}
