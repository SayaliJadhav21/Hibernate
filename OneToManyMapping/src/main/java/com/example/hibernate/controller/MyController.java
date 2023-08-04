package com.example.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernate.entity.Address;
import com.example.hibernate.entity.Student;
import com.example.hibernate.entity.Subject;
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
		
		List<Subject> subjects = new ArrayList<>();
		Subject subject1 = new Subject("Data Structures", student);
		Subject subject2 = new Subject("Science", student);
		Subject subject3 = new Subject("Mathematics", student);
		subjects.add(subject1);
		subjects.add(subject2);
		subjects.add(subject3);
		student.setSubjects(subjects);
		
		service.saveStudent(student);		
		return "Inserted!";
	}

}
