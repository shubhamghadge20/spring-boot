package com.example.demo.global;
import com.example.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;
public class GlobalData {

	public static List<Product> cart;
	
	static {
		cart=new ArrayList<Product>();
		
	}

}