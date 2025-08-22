package com.hrms.employee.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hrms.employee.service.EmployeeService;

import lombok.Data;


public class EmployeeRequestData {

	@NotBlank
	public String name;
	@NotNull
	public Role role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public Long deptId;
	public Long managerId;

}
