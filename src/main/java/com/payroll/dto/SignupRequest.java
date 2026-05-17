package com.payroll.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank String employeeId,
        @NotBlank String name,
        @NotBlank String department,
        @NotBlank String designation,
        @NotBlank String password,
        @NotBlank String confirmPassword
) {
}
