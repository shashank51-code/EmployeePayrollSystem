package com.payroll.dto;

import com.payroll.model.EmployeeStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRequest(
        @NotBlank String name,
        @NotBlank String department,
        @NotBlank String designation,
        @DecimalMin("0.0") Double basicSalary,
        EmployeeStatus status
) {
}
