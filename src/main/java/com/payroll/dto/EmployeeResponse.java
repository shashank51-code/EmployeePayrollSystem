package com.payroll.dto;

import com.payroll.model.Employee;
import com.payroll.model.EmployeeStatus;

public record EmployeeResponse(
        String employeeId,
        String name,
        String department,
        String designation,
        double basicSalary,
        EmployeeStatus status,
        double hra,
        double da,
        double ta,
        double grossSalary,
        double pf,
        double tax,
        double insurance,
        double totalDeductions,
        double netSalary
) {
    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getBasicSalary(),
                employee.getStatus(),
                employee.getHRA(),
                employee.getDA(),
                employee.getTA(),
                employee.getGrossSalary(),
                employee.getPF(),
                employee.getTax(),
                employee.getInsurance(),
                employee.getTotalDeductions(),
                employee.getNetSalary()
        );
    }
}
