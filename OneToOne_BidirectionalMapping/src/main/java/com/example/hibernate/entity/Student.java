package com.example.hibernate.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
@Component
public class Student {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String name;

//	Hibernate will automatically add forign key in student table we need not add through sql.
//	Hibernate: alter table address modify column address_lines  varchar(255)
//	Hibernate: alter table student modify column name  varchar(255)
//	Hibernate: alter table student add column address_id integer
//	Hibernate: alter table student drop index UK_e3500f0n5n132n60x2ay1woj9
//	Hibernate: alter table student add constraint UK_e3500f0n5n132n60x2ay1woj9 unique (address_id)
//	Hibernate: alter table student add constraint FKcaf6ht0hfw93lwc13ny0sdmvo foreign key (address_id) references address (id)	
	@OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL will save the address along with student entity we need not
											// write address.save()
	@JoinColumn(name = "address")// Join column name to be used
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
