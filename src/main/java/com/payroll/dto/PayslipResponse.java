package com.payroll.dto;

import java.time.LocalDateTime;

public record PayslipResponse(
        EmployeeResponse employee,
        LocalDateTime generatedAt
) {
}
