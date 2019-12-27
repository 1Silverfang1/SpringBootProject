package com.silverfang.boot.implement.dao;

import com.silverfang.boot.model.Category;
import com.silverfang.boot.repository.CategoryRepository;
import com.silverfang.boot.dao.CategoryServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements CategoryServiceDao {
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
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

}
