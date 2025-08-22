package com.hrms.department.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.department.model.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
