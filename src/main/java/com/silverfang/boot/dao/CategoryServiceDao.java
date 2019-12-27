package com.silverfang.boot.dao;

import com.silverfang.boot.model.Category;

import java.util.List;

public interface CategoryServiceDao {
        public List<Category> getCategory();
        public void saveCategory(Category category);
        public Category getsingleCategory(String name);
}
