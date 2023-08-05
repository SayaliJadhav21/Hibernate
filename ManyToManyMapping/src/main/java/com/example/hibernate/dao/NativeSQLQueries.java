package com.example.hibernate.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Subject;

import jakarta.transaction.Transactional;

@Repository
public class NativeSQLQueries {
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public List<Subject> getSubjects() {
		Session session = getSession();
		String sqlQuery = "select * from subject";
		NativeQuery<Subject> query = session.createNativeQuery(sqlQuery, Subject.class);
		
		query.setFirstResult(1);
		query.setMaxResults(10);
		
		List<Subject> subjectList = query.getResultList();
		subjectList.stream().map(subject -> subject.getName()).collect(Collectors.toSet()).forEach(name -> System.out.println(name + "\n"));
		return subjectList;
		
	}

}
