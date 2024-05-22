package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    
    @GetMapping("/admin")
    public String adminHome()
    {
    	return "adminHome";
    }
    
    @GetMapping("/admin/categories")
    public String getCategoryPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String getUpdateCategoryPage(@PathVariable Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "addCategory";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }
    
    @RequestMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model)
    {
    	Optional<Category>category=categoryService.getCategoryById(id);
    	if(category.isPresent())
    	{
    		model.addAttribute("category", category.get());
    	}
    	return "/admin/categories";
    }
    
//    @GetMapping("/admin/product")
//    public String getProduct() {
//        return "product"; 
//        }
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> product = productService.getAllProduct();
        model.addAttribute("product", product);
        return "product";
    }
    
    @GetMapping("/admin/product/add")
    public String getAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }
}