package com.fpt.doan.data.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Entity
@Component
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@ManyToMany(mappedBy = "department")
	private List<Book> book;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department(Integer id, String name, List<Book> book) {
		super();
		this.id = id;
		this.name = name;
		this.book = book;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", book=" + book + "]";
	}
}
