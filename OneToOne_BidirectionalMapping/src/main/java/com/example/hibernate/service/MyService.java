package com.example.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernate.dao.MyStuentDAO;
import com.example.hibernate.dao.StudentDAO;
import com.example.hibernate.entity.Student;

import jakarta.transaction.Transactional;

@Service
public class MyService {

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private MyStuentDAO mystudentDAO;

	// One to one mapping saving entity using crud repository
	@Transactional // if used on class it will apply to all public methods in class
	public void insertStudent(final Student student) {
		studentDAO.save(student);
	}

	// One to one mapping saving entity using sessionFactory
	@Transactional // if used on class it will apply to all public methods in class
	public void saveStudent(final Student student) {
		mystudentDAO.saveStudent(student);
	}
	
	@Transactional // if used on class it will apply to all public methods in class
	public Student getStudentFromAddress(final int address_id) {
		return mystudentDAO.getStudentFromAddress(address_id);
	}

}
