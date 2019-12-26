package com.silverfang.bootblog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Post,Integer> {
}
