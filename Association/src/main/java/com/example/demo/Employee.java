package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value ="prototype")

public class Employee {
	private int empid;
	private String ename;
	
	@Autowired
	Address addr; //dependend object
	
	public Employee() {
		System.out.println("Employee object is created");
	}

	public Employee(int empid, String ename, Address addr) {
		super();
		this.empid = empid;
		this.ename = ename;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", ename=" + ename + ", addr=" + addr + "]";
	}
	
	
	

	

}
