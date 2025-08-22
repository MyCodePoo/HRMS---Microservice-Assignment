package com.hrms.department.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hrms.department.service.DepartmentService;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner dataInit(DepartmentService service) {
       
    	return result -> {
            service.addDepartment("Engineering");
            service.addDepartment("Sales");
            service.addDepartment("HR");
        };
    }
}
