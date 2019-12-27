package com.silverfang.boot.dao;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostServiceDao {
    public List<Post> getPost();
    public void savePost(Post post);
    public Post getPostById(int id);
    public void editPost(Post post);
    public void deleteBlog(Post post);
    public List<Post> getPost(Pageable pageRequest);
    public List<Post> filterPost(Category filter,Pageable pageable);
    public List<Post> searchInPost(String key);
}
