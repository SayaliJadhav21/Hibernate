package com.example.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Student;

import jakarta.transaction.Transactional;

@Repository
public class HQLQueries {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public String getStudentNameUsingHQLQuery() {

		String name = "";
		String queryStr = "FROM Student where id=:id";
		Session session = getSession();
		Query query = session.createQuery(queryStr);
		query.setParameter("id", 1052);
		List list = query.list();
		System.out.println(list);
		for (Student student : (List<Student>)list) {
			name = student.getName().toString();
		}
		List list2 = query.getResultList();
		System.out.println(list2);
		Object list3 = query.getSingleResult();
		
		System.out.println(list3);
		return name;

	}

}
