package com.fpt.doan.service;

import org.springframework.stereotype.Service;

import com.fpt.doan.data.entity.Department;

@Service
public interface DepartmentService {

	Department getADepartment(Integer id);
}
