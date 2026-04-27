package com.payroll.dto;

public record DepartmentReport(
        String department,
        long employeeCount,
        double totalNetSalary,
        double averageNetSalary
) {
}
