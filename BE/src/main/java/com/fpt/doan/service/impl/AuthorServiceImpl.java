package com.fpt.doan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.doan.data.entity.Author;
import com.fpt.doan.data.repository.AuthorRepository;
import com.fpt.doan.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public Author getAAuthor(Integer id) {
		Author author = authorRepository.findById(id).get();
		return author;
	}

}
