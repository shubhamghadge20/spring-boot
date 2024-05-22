package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService 
{

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() 
    {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) 
    {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Integer id) 
    {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategoryById(Integer id) 
    {
        categoryRepository.deleteById(id);
    }
    
    public Optional<Category> getCategoryById(int id)
    {
    	return categoryRepository.findById(null);
    }
}