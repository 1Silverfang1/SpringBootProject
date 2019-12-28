package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.Category;

import java.util.List;

public interface CategoryServiceInterface {
        public List<Category> getCategory();
        public void saveCategory(Category category);
        public Category getsingleCategory(String name);
        public Category getCategory(String key);
}
