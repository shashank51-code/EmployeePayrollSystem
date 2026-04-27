package com.payroll.dto;

import com.payroll.model.PayslipRecord;

import java.time.LocalDateTime;

public record PayslipRecordResponse(
        Long id,
        String employeeId,
        String employeeName,
        String department,
        double netSalary,
        LocalDateTime generatedAt
) {
    public static PayslipRecordResponse from(PayslipRecord record) {
        return new PayslipRecordResponse(
                record.getId(),
                record.getEmployeeId(),
                record.getEmployeeName(),
                record.getDepartment(),
                record.getNetSalary(),
                record.getGeneratedAt()
        );
    }
}
