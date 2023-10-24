package com.fpt.doan.data.entity;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Component
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> author;

	@ManyToMany
	@JoinTable(name = "book_department", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
	private List<Department> department;

	private String description;

	@Column(name = "image")
	private String img;

	private String title;

	private Integer copies;

	private Integer copies_available;
	
	private String language;
	
	private Date date_import;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer id, String name, List<Author> author, List<Department> department, String description, String img,
			String title, Integer copies, Integer copies_available, String language, Date date_import) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.department = department;
		this.description = description;
		this.img = img;
		this.title = title;
		this.copies = copies;
		this.copies_available = copies_available;
		this.language = language;
		this.date_import = date_import;
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

	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public Integer getCopies_available() {
		return copies_available;
	}

	public void setCopies_available(Integer copies_available) {
		this.copies_available = copies_available;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getDate_import() {
		return date_import;
	}

	public void setDate_import(Date date_import) {
		this.date_import = date_import;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", department=" + department
				+ ", description=" + description + ", img=" + img + ", title=" + title + ", copies=" + copies
				+ ", copies_available=" + copies_available + ", language=" + language + ", date_import=" + date_import
				+ "]";
	}

	

}
