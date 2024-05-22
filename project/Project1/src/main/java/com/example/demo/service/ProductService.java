package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	 @Autowired
	 private ProductRepository productRepository;
	 
	 public List<Product> getAllProduct() 
	    {
	        return productRepository.findAll();
	    }
	 
	 public void saveProduct(Product product) 
	 {
		 productRepository.save(product);
	 }
	 

}
