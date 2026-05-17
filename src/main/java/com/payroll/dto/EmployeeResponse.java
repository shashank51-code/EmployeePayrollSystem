package com.payroll.dto;

import com.payroll.model.Employee;
import com.payroll.model.EmployeeStatus;

public record EmployeeResponse(
        String employeeId,
        String name,
        String department,
        String designation,
        Double basicSalary,
        EmployeeStatus status,
        Double hra,
        Double da,
        Double ta,
        Double grossSalary,
        Double pf,
        Double tax,
        Double insurance,
        Double totalDeductions,
        Double netSalary
) {
    public static EmployeeResponse from(Employee employee) {
        return from(employee, true);
    }

    public static EmployeeResponse publicFrom(Employee employee) {
        return from(employee, false);
    }

    public static EmployeeResponse from(Employee employee, boolean includeSalary) {
        return new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getDesignation(),
                includeSalary ? employee.getBasicSalary() : null,
                employee.getStatus(),
                includeSalary ? employee.getHRA() : null,
                includeSalary ? employee.getDA() : null,
                includeSalary ? employee.getTA() : null,
                includeSalary ? employee.getGrossSalary() : null,
                includeSalary ? employee.getPF() : null,
                includeSalary ? employee.getTax() : null,
                includeSalary ? employee.getInsurance() : null,
                includeSalary ? employee.getTotalDeductions() : null,
                includeSalary ? employee.getNetSalary() : null
        );
    }
}
