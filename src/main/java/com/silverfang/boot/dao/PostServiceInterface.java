package com.silverfang.boot.dao;

import com.silverfang.boot.model.Post;

import java.util.List;

public interface PostServiceInterface {
    public List<Post> getPost();
    public void savePost(Post post);
    public Post getPostById(int id);
    public void editPost(Post post);
    public void deleteBlog(Post post);
}
