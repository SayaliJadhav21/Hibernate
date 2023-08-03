package com.example.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernate.entity.Address;
import com.example.hibernate.entity.Student;
import com.example.hibernate.service.MyService;

@RestController
public class MyController {
	
	@Autowired
	private MyService service;
	
	@PostMapping("/insertStudent")
	public String insertStudent(@RequestBody Student student) {
		Address address = new Address();
		address.setAddress_lines("Nashik");
		student.setAddress(address);
		service.insertStudent(student);		
		return "Inserted!";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@RequestBody Student student) {
		Address address = new Address();
		address.setAddress_lines("Nashik");
		student.setAddress(address);
		service.saveStudent(student);		
		return "Inserted!";
	}

	@GetMapping("/getStudentFromAddress")
	public Student getStudentFromAddress() {
		Student s = service.getStudentFromAddress(252);
		System.out.println(s);
		return s;
	}
}
