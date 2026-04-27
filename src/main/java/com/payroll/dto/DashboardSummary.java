package com.payroll.dto;

import java.util.List;

public record DashboardSummary(
        long totalEmployees,
        long totalDepartments,
        double totalSalaryPayout,
        double averageSalary,
        EmployeeResponse highestPaidEmployee,
        EmployeeResponse lowestPaidEmployee,
        List<EmployeeResponse> topEmployees,
        List<DepartmentReport> departmentReports,
        List<PayslipRecordResponse> recentPayslips
) {
}
