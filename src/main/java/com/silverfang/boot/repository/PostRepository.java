package com.silverfang.boot.repository;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findAllBy(Pageable pageable);
    List<Post> findPostByListCategoryIsContaining(Category tag, Pageable pageable);
    List<Post> findPostByListCategoryIsContainingOrderByContent(Category tag, Pageable pageable);
    List<Post> findPostByListCategoryIsContainingOrderByTitle(Category tag, Pageable pageable);
    List<Post> findPostByListCategoryIsContainingOrderByCreatedAt(Category tag, Pageable pageable);
    List<Post> findPostByListCategoryIsContainingOrderByUpdatedAt(Category tag, Pageable pageable);
    List<Post> findPostByUserTable(UserTable userTable,Pageable pageable);
    List<Post> findPostByTitleContainsOrContentContains(String key, String key2, Pageable pageable);
}

