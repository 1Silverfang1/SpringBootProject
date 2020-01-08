package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostServiceInterface {
    List<Post> getPost();
    void savePost(Post post);
    Post getPostById(int id);
    void editPost(Post post);
    void deleteBlog(Post post);
    List<Post> getPost(Pageable pageRequest);
    List<Post> filterPost(Category filter, Pageable pageable);
    List<Post> filterPostByTitle(Category filter,Pageable pageable);
    List<Post> filterPostByContent(Category filter,Pageable pageable);
    List<Post> filterPostCreatedAt(Category filter,Pageable pageable);
    List<Post> filterPostUpdatedAt(Category filter,Pageable pageable);

    public List<Post> searchInPost(String key,Pageable pageable);
}
