package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User; 


@Repository
public interface UserRepository  extends  JpaRepository<User,Integer>{
	User findByEmail(String email);

	
}