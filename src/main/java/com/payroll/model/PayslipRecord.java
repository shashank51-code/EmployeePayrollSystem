package com.payroll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "payslip_records")
public class PayslipRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private String employeeName;
    private String department;
    private double netSalary;
    private LocalDateTime generatedAt;

    protected PayslipRecord() {
    }

    public PayslipRecord(String employeeId, String employeeName, String department, double netSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.netSalary = netSalary;
        this.generatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
