package com.silverfang.boot.repository;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    public List<Post> findAllBy(Pageable pageable);
    public List<Post> findAllByListCategory(String tag);
    public List<Post> findPostByListCategoryIsContaining(Category tag,Pageable pageable);
    public List<Post> findPostByTitleContainsOrContentContains(String key,String key2);
}

