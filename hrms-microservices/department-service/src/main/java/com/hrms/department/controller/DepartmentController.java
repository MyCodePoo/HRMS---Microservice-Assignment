package com.hrms.department.controller;

import org.springframework.web.bind.annotation.*;

import com.hrms.department.model.Department;
import com.hrms.department.model.DepartmentRequestData;
import com.hrms.department.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private DepartmentService service;

	public DepartmentController(DepartmentService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public Department addDepartment(@Valid @RequestBody DepartmentRequestData request) {
		return this.service.addDepartment(request.getName());
	}

	@GetMapping("/{deptId}")
	public Department get(@PathVariable Long deptId) {
		return this.service.get(deptId);
	}

	@GetMapping("/view")
	public List<Department> list() {
		return this.service.list();
	}
}
