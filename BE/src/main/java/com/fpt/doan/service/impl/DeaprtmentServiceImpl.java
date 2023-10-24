package com.fpt.doan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.doan.data.entity.Department;
import com.fpt.doan.data.repository.DepartmentRepository;
import com.fpt.doan.service.DepartmentService;

@Service
public class DeaprtmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public Department getADepartment(Integer id) {
		return departmentRepository.findById(id).get();
	}


}
