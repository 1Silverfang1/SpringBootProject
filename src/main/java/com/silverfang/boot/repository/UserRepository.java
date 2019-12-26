package com.silverfang.boot.repository;

import com.silverfang.boot.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTable,Integer> {
}
