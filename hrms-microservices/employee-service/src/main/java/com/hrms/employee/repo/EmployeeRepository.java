package com.hrms.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.employee.model.Employee;
import com.hrms.employee.model.Role;

import java.util.*;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByRole(Role role);
    java.util.List<Employee> findByDepartmentId(Long deptId);
    java.util.List<Employee> findByManagerId(Long managerId);
}
