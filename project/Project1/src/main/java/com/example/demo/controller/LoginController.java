package com.example.demo.controller;

import java.security.Principal; 

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserDTO;
// com.example.demo.global.GlobalData;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	//user registration
	
	@GetMapping("/registration")
	public String getRegistration()
	{
		return "register";
	}
	
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user")UserDTO userDTO,Model model)
	{
		userDTO.setRole("USER");
		userService.save(userDTO);
		model.addAttribute("message", "Resistered Successfully");
		return "register"; 
	}

	@GetMapping("/login")
	public String login()	
	{
	//GlobalData.cart.clear();
	return "login";
	}
}