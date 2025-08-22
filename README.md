#Project Name = HRMS System 

##Problem statement -

Employee, Department

a. Every employee must belong to 1 dept
b. Every employee must have 1 manager (manager is also employee)
b employee and manager must belong to same dept
c. every dept has 1 employee as dept head
d. all dept heads, report to 1 employee, who is titled CEO.
 
Exceptions to above rules:
a. CEO has no manager
b. CEO does not belong to a dept
c. 2 employees from same department should not be direct reporties to the CEO
 
Initial Setup:
3 departments
4 employees, 1 CEO, 3 dept heads
 
Abilities:
Add new Employee
Add new Dept
Move employee from one dept to another
View Employees of a dept
View employees reporting to a Manager

##Design this solution using java 8 , spring boot & microservice architecture , H2 (In memory DB for easy testing purpose)


##**Execution Guide **
Execution order for microservices

1.service-registry (Eureka) → runs on :8761

2.api-gateway 

3.department-service

4.employee-service (uses Feign to call department-service)

Confirm Eureka Dashboard: http://localhost:8761
All requests are made via Gateway at http://localhost:8080/...

Collection of postman API also attached for reference 
