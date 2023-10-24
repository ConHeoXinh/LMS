package com.fpt.doan.service;

import org.springframework.stereotype.Service;

import com.fpt.doan.data.entity.Author;

@Service
public interface AuthorService {

	Author getAAuthor(Integer id);
}
