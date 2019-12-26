package com.silverfang.boot.repository;

import com.silverfang.boot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
