package com.payroll.service;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String employeeId) {
        super("Employee not found for ID: " + employeeId);
    }
}
