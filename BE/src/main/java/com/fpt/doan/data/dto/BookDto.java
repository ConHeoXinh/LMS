package com.fpt.doan.data.dto;

import java.util.Date;
import java.util.List;

import com.fpt.doan.data.entity.Author;
import com.fpt.doan.data.entity.Department;

public class BookDto {

	private Integer id;
	private String name;
	private List<Author> author;
	private List<Department> department;
	private String description;
	private String img;
	private String title;
	private Integer copies;
	private Integer copies_available;
	private String language;
	private Date date_import;

	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDto(Integer id, String name, List<Author> author, List<Department> department, String description,
			String img, String title, Integer copies, Integer copies_available, String language, Date date_import) {
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

}
