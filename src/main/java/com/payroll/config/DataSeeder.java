package com.payroll.config;

import com.payroll.model.Employee;
import com.payroll.model.EmployeeStatus;
import com.payroll.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public DataSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        if (employeeRepository.count() > 0) {
            return;
        }

        employeeRepository.save(new Employee("EMP1001", "Arjun Mehta", "Engineering", "Software Engineer", 55000.0, EmployeeStatus.ACTIVE));
        employeeRepository.save(new Employee("EMP1002", "Sneha Reddy", "HR", "HR Manager", 48000.0, EmployeeStatus.ACTIVE));
        employeeRepository.save(new Employee("EMP1003", "Kiran Rao", "Finance", "Accountant", 42000.0, EmployeeStatus.ACTIVE));
        employeeRepository.save(new Employee("EMP1004", "Divya Nair", "Engineering", "Senior Developer", 70000.0, EmployeeStatus.ACTIVE));
        employeeRepository.save(new Employee("EMP1005", "Rahul Sharma", "Marketing", "Marketing Lead", 38000.0, EmployeeStatus.ACTIVE));
    }
}
