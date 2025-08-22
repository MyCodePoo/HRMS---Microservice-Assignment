package com.hrms.employee.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hrms.employee.service.EmployeeService;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner dataInit(EmployeeService service) {
        return result -> {
            // CEO
            Employee ceo = service.addEmployee("Poonam K", Role.CEO, null, null);

            //Dept Head reporting to CEO  
            Employee h1 = service.addEmployee("Shravti R", Role.DEPT_HEAD, 1L, ceo.getId());
            Employee h2 = service.addEmployee("Karishma K", Role.DEPT_HEAD, 2L, ceo.getId());
            Employee h3 = service.addEmployee("Amit D", Role.DEPT_HEAD, 3L, ceo.getId());

            // Staff data against that dept
            service.addEmployee("Amol D", Role.STAFF, 1L, h1.getId());
            service.addEmployee("Dipika R", Role.STAFF, 1L, h1.getId());
            service.addEmployee("Pravin K", Role.STAFF, 2L, h2.getId());
            service.addEmployee("Raddika Chand", Role.STAFF, 1L, h1.getId());
        };
    }
}
