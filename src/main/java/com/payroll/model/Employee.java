package com.payroll.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(length = 20)
    private String employeeId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String department;

    @NotBlank
    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private Double basicSalary = 0.0;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Employee() {
    }

    public Employee(String employeeId, String name, String department, String designation, Double basicSalary, EmployeeStatus status) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary == null ? 0.0 : basicSalary;
        this.status = status == null ? EmployeeStatus.ACTIVE : status;
    }

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public double getHRA() {
        return basicSalary * 0.20;
    }

    public double getDA() {
        return basicSalary * 0.10;
    }

    public double getTA() {
        return basicSalary * 0.05;
    }

    public double getGrossSalary() {
        return basicSalary + getHRA() + getDA() + getTA();
    }

    public double getPF() {
        return basicSalary * 0.12;
    }

    public double getTax() {
        return basicSalary * 0.08;
    }

    public double getInsurance() {
        return basicSalary * 0.02;
    }

    public double getTotalDeductions() {
        return getPF() + getTax() + getInsurance();
    }

    public double getNetSalary() {
        return getGrossSalary() - getTotalDeductions();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary == null ? 0.0 : basicSalary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
