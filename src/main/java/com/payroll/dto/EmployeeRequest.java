package com.payroll.dto;

import com.payroll.model.EmployeeStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequest(
        @NotBlank String name,
        @NotBlank String department,
        @NotBlank String designation,
        @NotNull @DecimalMin("1.0") Double basicSalary,
        EmployeeStatus status
) {
}
