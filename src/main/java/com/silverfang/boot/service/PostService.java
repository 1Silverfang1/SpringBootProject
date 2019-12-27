package com.silverfang.boot.service;

import com.silverfang.boot.model.Post;
import com.silverfang.boot.repository.PostRepository;
import com.silverfang.boot.dao.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService implements PostServiceInterface {
    @Autowired
    private PostRepository postRepository;
    public List<Post> getPost()
    {
     return  postRepository.findAll();
    }
    public void savePost(Post post)
    {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(int postId) {
        return  postRepository.findById(postId).get();
    }

    @Override
    public void editPost(Post post) {

    }

    @Override
    public void deleteBlog(Post post)
    {
    postRepository.delete(post);
    }
}
