package com.payroll.controller;

import com.payroll.dto.DashboardSummary;
import com.payroll.dto.DepartmentReport;
import com.payroll.dto.EmployeeRequest;
import com.payroll.dto.EmployeeResponse;
import com.payroll.dto.PayslipRecordResponse;
import com.payroll.dto.PayslipResponse;
import com.payroll.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PayrollController {
    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping("/dashboard")
    public DashboardSummary dashboard(Authentication authentication) {
        return payrollService.dashboardSummary(isAdmin(authentication));
    }

    @GetMapping("/employees")
    public List<EmployeeResponse> employees(Authentication authentication) {
        return payrollService.listEmployees(isAdmin(authentication));
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeResponse employee(@PathVariable String employeeId, Authentication authentication) {
        return payrollService.getEmployee(employeeId, isAdmin(authentication) || authentication.getName().equalsIgnoreCase(employeeId));
    }

    @GetMapping("/profile")
    public EmployeeResponse profile(Authentication authentication) {
        return payrollService.getEmployee(authentication.getName(), true);
    }

    @PutMapping("/profile")
    public EmployeeResponse updateProfile(@Valid @RequestBody EmployeeRequest request, Authentication authentication) {
        return payrollService.updateEmployeeProfile(authentication.getName(), request);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@Valid @RequestBody EmployeeRequest request) {
        return payrollService.addEmployee(request);
    }

    @PutMapping("/employees/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable String employeeId, @Valid @RequestBody EmployeeRequest request) {
        return payrollService.updateEmployee(employeeId, request);
    }

    @DeleteMapping("/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable String employeeId) {
        payrollService.deleteEmployee(employeeId);
    }

    @PostMapping("/payslips/{employeeId}")
    public PayslipResponse generatePayslip(@PathVariable String employeeId, Authentication authentication) {
        if (!isAdmin(authentication) && !authentication.getName().equalsIgnoreCase(employeeId)) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.FORBIDDEN, "Employees can generate only their own payslip");
        }
        return payrollService.generatePayslip(employeeId);
    }

    @GetMapping("/payslips")
    public List<PayslipRecordResponse> payslipHistory() {
        return payrollService.payslipHistory();
    }

    @DeleteMapping("/payslips")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearPayslipHistory() {
        payrollService.clearPayslipHistory();
    }

    @GetMapping("/reports/departments")
    public List<DepartmentReport> departmentReports() {
        return payrollService.departmentReports();
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);
    }
}
