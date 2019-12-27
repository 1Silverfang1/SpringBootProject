package com.silverfang.boot.repository;

import com.silverfang.boot.model.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    public List<Post> findAllBy(Pageable pageable);
}

