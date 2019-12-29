package com.silverfang.boot.service.helper;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.repository.CategoryRepository;
import com.silverfang.boot.interfaces.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements CategoryServiceInterface {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category getsingleCategory(String name)
    {
        return categoryRepository.findById(name).get();
    }

    @Override
    public Category getCategory(String key) {
        return categoryRepository.findCategoryByNameContains(key);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

}
