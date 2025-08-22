package com.hrms.department.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.hrms.department.model.Department;
import com.hrms.department.repo.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository repo;

	public DepartmentService(DepartmentRepository repo) {
		this.repo = repo;
	}

	public java.util.List<Department> list() {
		return repo.findAll();
	}

	@Transactional
	public Department addDepartment(String name) {
		if (repo.findByName(name).isPresent()) {
			throw new IllegalArgumentException("Department already exists");
		}
		
		Department dept = new Department();
		dept.setName(name);
		return repo.save(dept);
	}

	public Department get(Long id) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found in database"));
	}
}
