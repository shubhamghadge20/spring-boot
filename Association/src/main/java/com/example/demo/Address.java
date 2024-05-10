package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value ="prototype")

public class Address {
	
	private String City;
	private String State;
	private int pincode;
	
	

	public  Address() {
		
		System.out.print("Address object is created");
		
	}
	//create constructor and fields
	public Address(String city, String state, int pincode) {
		super();
		City = city;
		State = state;
		this.pincode = pincode;
	}
	//toString
	@Override
	public String toString() {
		return "Address [City=" + City + ", State=" + State + ", pincode=" + pincode + "]";
	}

	
	
	

	}


