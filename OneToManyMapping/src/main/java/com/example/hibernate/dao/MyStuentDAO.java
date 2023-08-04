package com.example.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Student;

import jakarta.transaction.Transactional;

@Repository
public class MyStuentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void saveStudent(Student student) {
		Session session = getSession();
		session.save(student);
	}

}
