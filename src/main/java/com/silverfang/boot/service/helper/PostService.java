package com.silverfang.boot.service.helper;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.repository.PostRepository;
import com.silverfang.boot.interfaces.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public List<Post> getPost(Pageable pageRequest)

    {
        return  postRepository.findAllBy(pageRequest);
    }

    @Override
    public List<Post> filterPost(Category filter , Pageable pageable) {
        return postRepository.findPostByListCategoryIsContaining(filter,pageable);
    }

    @Override
    public List<Post> filterPostByTitle(Category filter, Pageable pageable) {
        return postRepository.findPostByListCategoryIsContainingOrderByTitle(filter,pageable);
    }

    @Override
    public List<Post> filterPostByContent(Category filter, Pageable pageable) {
        return postRepository.findPostByListCategoryIsContainingOrderByContent(filter, pageable);
    }

    @Override
    public List<Post> filterPostCreatedAt(Category filter, Pageable pageable) {
        return postRepository.findPostByListCategoryIsContainingOrderByCreatedAt(filter,pageable);
    }

    @Override
    public List<Post> filterPostUpdatedAt(Category filter, Pageable pageable) {
        return postRepository.findPostByListCategoryIsContainingOrderByUpdatedAt(filter,pageable);
    }


    @Override
    public List<Post> searchInPost(String key,Pageable pageable) {
       return postRepository.findPostByTitleContainsOrContentContains(key,key,pageable);
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
