package com.example.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String address_lines;

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
	
	

}
