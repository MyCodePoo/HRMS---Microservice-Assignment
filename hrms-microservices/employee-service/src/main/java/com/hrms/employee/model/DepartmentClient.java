package com.hrms.employee.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {
    @GetMapping("/departments/{id}")
    DepartmentContainer get(@PathVariable("id") Long id);

    class DepartmentContainer {
        public Long id;
        public String name;
    }
}
