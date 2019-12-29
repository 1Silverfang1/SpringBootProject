package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostServiceInterface {
    public List<Post> getPost();
    public void savePost(Post post);
    public Post getPostById(int id);
    public void editPost(Post post);
    public void deleteBlog(Post post);
    public List<Post> getPost(Pageable pageRequest);
    public List<Post> filterPost(Category filter,Pageable pageable);
    List<Post> filterPostByTitle(Category filter,Pageable pageable);
    List<Post> filterPostByContent(Category filter,Pageable pageable);
    List<Post> filterPostCreatedAt(Category filter,Pageable pageable);
    List<Post> filterPostUpdatedAt(Category filter,Pageable pageable);

    public List<Post> searchInPost(String key,Pageable pageable);
}
