package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller

public class HomeController 
{
  @Autowired
  CategoryService categoryService;
  @Autowired
  ProductService productService;
  
  @GetMapping("/index")
  public String home(Model model)
  {
	  return "index";
  }
  
  @GetMapping("/shop")
  public String shop(Model model)
  {
	   model.addAttribute("cartCount", GlobalData.cart.size());
	  model.addAttribute("categories", categoryService.getAllCategories());
	  model.addAttribute("products", productService.getAllProduct());
	  return "shop";
  }
  
  // for shop allproduct or specific product
  @GetMapping("/shop/category/{id}")
  public String shopByCategory(Model model ,@PathVariable int id) {
	   model.addAttribute("cartCount", GlobalData.cart.size());
  	model.addAttribute("categories",categoryService.getAllCategories());
  	model.addAttribute("products",productService.getProductByCategoryId(id));
  	return "shop";
  }

  
  
  // for view product
  @GetMapping("/shop/viewproduct/{id}")
  public String viewProduct(@PathVariable Long id, Model model) {
	   model.addAttribute("cartCount", GlobalData.cart.size());
      model.addAttribute("product", productService.getProductById(id).get());
      return "viewproduct"; 
  }
}