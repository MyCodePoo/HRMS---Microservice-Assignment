package com.hrms.department.model;

import javax.validation.constraints.NotBlank;

import com.hrms.department.service.DepartmentService;

public class DepartmentRequestData {

	@NotBlank
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}