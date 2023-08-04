package com.example.hibernate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hibernate.entity.Student;

@Repository
public interface StudentDAO extends CrudRepository<Student, Integer>{
	
}
