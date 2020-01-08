package com.silverfang.boot.interfaces;

import com.silverfang.boot.model.Category;

import java.util.List;

public interface CategoryServiceInterface {
        List<Category> getCategory();
        void saveCategory(Category category);
        Category getSingleCategory(String name);
        Category getCategory(String key);
}
