package com.fpt.doan.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.doan.data.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
}
