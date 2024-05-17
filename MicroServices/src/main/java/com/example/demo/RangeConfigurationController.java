package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;  
 
@RestController  
public class RangeConfigurationController   
{  
@Autowired    
private Configuration configuration;      
@GetMapping("/range")  
public RangeConfiguration retriveLimitsFromConfigurations()  
{  
//getting values from the properties file  
return new RangeConfiguration(configuration.getMaximum(), configuration.getMinimum());
	//return new RangeConfiguration(3000,30);
}  
}  