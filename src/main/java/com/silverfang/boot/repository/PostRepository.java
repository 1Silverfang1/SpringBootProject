package com.silverfang.boot.repository;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    public List<Post> findAllBy(Pageable pageable);
    public List<Post> findAllByListCategory(String tag);
    public List<Post> findPostByListCategoryIsContaining(Category tag,Pageable pageable);
    public List<Post> findPostByListCategoryIsContainingOrderByContent(Category tag,Pageable pageable);
    public List<Post> findPostByListCategoryIsContainingOrderByTitle(Category tag,Pageable pageable);
    public List<Post> findPostByListCategoryIsContainingOrderByCreatedAt(Category tag,Pageable pageable);
    public List<Post> findPostByListCategoryIsContainingOrderByUpdatedAt(Category tag,Pageable pageable);
    List<Post> findPostByUserTable(UserTable userTable,Pageable pageable);
    public List<Post> findPostByTitleContainsOrContentContains(String key,String key2,Pageable pageable);
}

