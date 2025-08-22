package com.hrms.employee.controller;

import org.springframework.web.bind.annotation.*;

import com.hrms.employee.model.Employee;
import com.hrms.employee.model.EmployeeRequestData;
import com.hrms.employee.model.Move;
import com.hrms.employee.model.Role;
import com.hrms.employee.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	// Add new Employee
	@PostMapping("/add")
	public Employee addEmployee(@Valid @RequestBody EmployeeRequestData request) {
		return service.addEmployee(request.getName(), request.getRole(), request.getDeptId(), request.getManagerId());
	}

	// Move employee from one dept to another
	@PatchMapping("/{id}/move")
	public Employee move(@PathVariable Long id, @Valid @RequestBody Move request) {
		return service.moveEmployee(id, request.targetDeptId);
	}

	// View Employees of a dept
	@GetMapping("/{id}/view")
	public List<Employee> viewEmployeeByDept(@PathVariable Long id) {
		return service.employeesOfDept(id);
	}

	// View employees reporting to a Manager
	@GetMapping("/manager/{managerId}/reports")
	public List<Employee> viewEmployeesToReportingManager(@PathVariable Long managerId) {
		return service.reportsOfManager(managerId);
	}
}
