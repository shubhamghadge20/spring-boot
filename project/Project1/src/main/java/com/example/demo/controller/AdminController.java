package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

	   public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/images";

    @Autowired
    private CategoryService categoryService;

    
    @GetMapping("/admin")
    public String adminHome()
    {
    	return "adminHome";
    }
    
  //Category Section
    @GetMapping("/admin/categories")
    public String getCategoryPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }
   
  //add new category
    @GetMapping("/admin/categories/add")
    public String getAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    // save category
    @PostMapping("/admin/categories/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }
    
  //update category
    @GetMapping("/admin/categories/update/{id}")
    public String getUpdateCategoryPage(@PathVariable Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "addCategory";
    }
    
    //delete category
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
    
    @Autowired 
    private ProductRepository productRpository;
    
 	@GetMapping("/admin/product")
 	public String product(Model model)
 	{
 		model.addAttribute("products",productService.getAllProduct());
 		return "product";
 	}
    
 	//add product
    @GetMapping("/admin/product/add")
	public String addProduct(Model model )
	{
		
     model.addAttribute("productDTO",new ProductDTO());
     model.addAttribute("categories",categoryService.getAllCategories());
		return "addProduct";
	}
	
    //save product
	@PostMapping("/admin/product/save")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException{
		
		Product product = new Product();
		product.setProduct_id(productDTO.getProduct_id());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		String imageUUID;
  		if(!file.isEmpty())
  		{
  			imageUUID=file.getOriginalFilename();
  			Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
  			Files.write(fileNameAndPath, file.getBytes());
  			
  		}
  		else
  		{
  			imageUUID=imgName;
  		}
  		product.setImgName(imageUUID);
  		
		productService.saveProduct(product);
		return "redirect:/admin/product";
	}
	
	//delete product
		@RequestMapping("/admin/product/delete/{id}")
		public String delProduct(@PathVariable long id)
		{
			productService.removeProductById(id);
			return "redirect:/admin/product";
		}
		
		//update product
		@RequestMapping("/admin/product/update/{id}")
		public String updateProduct(@PathVariable long id,Model model)
		{
			ProductDTO productDTO=new ProductDTO();
			Product product=productService.getProductById(id).get();
			productDTO.setProduct_id(product.getProduct_id());
			productDTO.setName(product.getName());
			productDTO.setCategoryId(product.getCategory().getId());
			productDTO.setPrice(product.getPrice());
			productDTO.setWeight(product.getWeight());
			productDTO.setImgName(product.getImgName());
			
			model.addAttribute("categories",categoryService. getAllCategories());
			model.addAttribute("productDTO",productDTO);
			return "addProduct";
		}
		
		
		//for admin registration
		@Autowired
				private UserService userService;
						
		
		@GetMapping("admin/registration")
				public String saveUser(@ModelAttribute("admin")UserDTO userDTO,Model model)
				{
					userDTO.setRole("ADMIN");
					userService.save(userDTO);
					model.addAttribute("message", "Resistered Successfully");
					return "register"; 
				}

	
	
}

	  
