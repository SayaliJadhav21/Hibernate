package com.example.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String address_lines;

	@OneToOne(mappedBy = "address") // If we don't specify mappedBy then reference column will be added to both
									// tables student and Address. MappedBy = "address" will add refernece column in
									// Student table and no column will be added in Address table.
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress_lines() {
		return address_lines;
	}

	public void setAddress_lines(String address_lines) {
		this.address_lines = address_lines;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
