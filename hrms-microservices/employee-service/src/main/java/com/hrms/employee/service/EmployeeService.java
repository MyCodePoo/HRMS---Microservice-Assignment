package com.hrms.employee.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hrms.employee.model.DepartmentClient;
import com.hrms.employee.model.Employee;
import com.hrms.employee.model.Role;
import com.hrms.employee.repo.EmployeeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

	private final EmployeeRepository repo;
	private final DepartmentClient departments;

	public EmployeeService(EmployeeRepository repo, DepartmentClient departments) {
		this.repo = repo;
		this.departments = departments;
	}

	@Transactional
	public Employee addEmployee(String name, Role role, Long deptId, Long managerId) {

		if (role == Role.CEO) {
			return createEmployeeIfRoleAsCEO(name, deptId, managerId);
		}

		ensureDepartmentExists(deptId);

		validationBeforeCreateRecords(role, deptId, managerId);

		Employee e = new Employee();
		e.setName(name);
		e.setRole(role);
		e.setDepartmentId(deptId);
		e.setManagerId(managerId);

		return repo.save(e);
	}

	@Transactional
	public Employee moveEmployee(Long empId, Long targetDeptId) {
		Employee e = requireEmployee(empId, "Employee");
		Assert.isTrue(e.getRole() != Role.CEO, "CEO cannot belong to a department");
		ensureDepartmentExists(targetDeptId);

		Employee mgr = e.getManagerId() == null ? null : requireEmployee(e.getManagerId(), "Manager");
		if (mgr != null && mgr.getRole() != Role.CEO) {

			Assert.isTrue(Objects.equals(mgr.getDepartmentId(), targetDeptId),
					"Cannot move: manager is in a different department");
		}
		if (mgr != null && mgr.getRole() == Role.CEO) {
			boolean existsFromSameDept = repo.findByManagerId(mgr.getId()).stream()
					.filter(x -> !Objects.equals(x.getId(), e.getId()))
					.anyMatch(x -> x.getDepartmentId() != null && Objects.equals(x.getDepartmentId(), targetDeptId));
			Assert.isTrue(!existsFromSameDept, "CEO already has a direct report from that department");
		}
		if (e.getRole() == Role.DEPT_HEAD && Objects.equals(e.getDepartmentId(), targetDeptId)) {

			Assert.isTrue(Objects.equals(e.getDepartmentId(), targetDeptId),
					"Department head cannot move to a different department");
			throw new IllegalArgumentException("Department head cannot move to a different department");
		}
		e.setDepartmentId(targetDeptId);
		return repo.save(e);
	}

	public List<Employee> employeesOfDept(Long deptId) {
		return repo.findByDepartmentId(deptId);
	}

	public List<Employee> reportsOfManager(Long managerId) {
		return repo.findByManagerId(managerId);
	}

	private void validationBeforeCreateRecords(Role role, Long deptId, Long managerId) {
		if (managerId == null)
			throw new IllegalArgumentException("managerId required");
		Employee manager = requireEmployee(managerId, "Manager");

		if (manager.getRole() != Role.CEO) {
			Assert.isTrue(Objects.equals(manager.getDepartmentId(), deptId),
					"Employee and manager must be in the same department (unless manager is CEO)");
		} else {
			boolean existsFromSameDept = repo.findByManagerId(manager.getId()).stream()
					.anyMatch(e -> e.getDepartmentId() != null && Objects.equals(e.getDepartmentId(), deptId));

			Assert.isTrue(!existsFromSameDept, "Only one direct report to CEO per department (the dept head)");

		}

		if (role == Role.DEPT_HEAD) {
			 Assert.isTrue(manager.getRole() == Role.CEO, "Department head must report to CEO");
		

			boolean headExists = repo.findByDepartmentId(deptId).stream().anyMatch(x -> x.getRole() == Role.DEPT_HEAD);

			
			 Assert.isTrue(!headExists, "Department already has a head");
		}
	}

	private Employee createEmployeeIfRoleAsCEO(String name, Long deptId, Long managerId) {
		if (repo.findByRole(Role.CEO).isPresent())
			throw new IllegalArgumentException("There can be only one CEO");

		 Assert.isTrue(deptId == null, "CEO must not belong to a department");
		 Assert.isTrue(managerId == null, "CEO must not have a manager");

		Employee ceo = new Employee();
		ceo.setName(name);
		ceo.setRole(Role.CEO);
		return repo.save(ceo);
	}

	private void ensureDepartmentExists(Long id) {
		if (id == null)
			throw new IllegalArgumentException("deptId required");
		departments.get(id); 
	}

	private Employee requireEmployee(Long id, String label) {
		return repo.findById(id).orElseThrow(() -> new IllegalArgumentException(label + " not found"));
	}
}
