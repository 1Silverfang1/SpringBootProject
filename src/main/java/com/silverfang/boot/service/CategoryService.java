package com.silverfang.boot.service;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.repository.CategoryRepository;
import com.silverfang.boot.dao.CategoryServiceInterface;
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

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategory(int id) {
        return categoryRepository.findById(id).get();
    }
}
