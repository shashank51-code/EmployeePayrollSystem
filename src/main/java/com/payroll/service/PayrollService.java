package com.payroll.service;

import com.payroll.dto.DashboardSummary;
import com.payroll.dto.DepartmentReport;
import com.payroll.dto.EmployeeRequest;
import com.payroll.dto.EmployeeResponse;
import com.payroll.dto.PayslipRecordResponse;
import com.payroll.dto.PayslipResponse;
import com.payroll.dto.SignupRequest;
import com.payroll.model.Employee;
import com.payroll.model.EmployeeStatus;
import com.payroll.model.PayslipRecord;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.PayslipRecordRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayrollService {
    private final EmployeeRepository employeeRepository;
    private final PayslipRecordRepository payslipRecordRepository;
    private final PasswordEncoder passwordEncoder;

    public PayrollService(EmployeeRepository employeeRepository, PayslipRecordRepository payslipRecordRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.payslipRecordRepository = payslipRecordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<EmployeeResponse> listEmployees() {
        return listEmployees(true);
    }

    public List<EmployeeResponse> listEmployees(boolean includeSalary) {
        return employeeRepository.findAll().stream()
                .sorted(Comparator.comparing(Employee::getEmployeeId))
                .map(employee -> EmployeeResponse.from(employee, includeSalary))
                .toList();
    }

    public EmployeeResponse getEmployee(String employeeId) {
        return getEmployee(employeeId, true);
    }

    public EmployeeResponse getEmployee(String employeeId, boolean includeSalary) {
        return EmployeeResponse.from(findEmployee(employeeId), includeSalary);
    }

    public EmployeeResponse addEmployee(EmployeeRequest request) {
        Employee employee = new Employee(nextEmployeeId(), request.name().trim(), request.department().trim(),
                request.designation().trim(), request.basicSalary(), safeStatus(request.status()));
        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    public EmployeeResponse signup(SignupRequest request) {
        String employeeId = request.employeeId().trim().toUpperCase();
        if (employeeRepository.existsById(employeeId)) {
            throw new IllegalArgumentException("Employee ID already exists");
        }
        if (!request.password().equals(request.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Employee employee = new Employee(employeeId, request.name().trim(), request.department().trim(),
                request.designation().trim(), 0.0, EmployeeStatus.ACTIVE);
        employee.setPassword(passwordEncoder.encode(request.password()));
        return EmployeeResponse.publicFrom(employeeRepository.save(employee));
    }

    public EmployeeResponse updateEmployee(String employeeId, EmployeeRequest request) {
        Employee employee = findEmployee(employeeId);
        employee.setName(request.name().trim());
        employee.setDepartment(request.department().trim());
        employee.setDesignation(request.designation().trim());
        employee.setBasicSalary(request.basicSalary());
        employee.setStatus(safeStatus(request.status()));
        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    public EmployeeResponse updateEmployeeProfile(String employeeId, EmployeeRequest request) {
        Employee employee = findEmployee(employeeId);
        employee.setName(request.name().trim());
        employee.setDepartment(request.department().trim());
        employee.setDesignation(request.designation().trim());
        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    public void deleteEmployee(String employeeId) {
        if (!employeeRepository.existsById(employeeId.toUpperCase())) {
            throw new EmployeeNotFoundException(employeeId);
        }
        employeeRepository.deleteById(employeeId.toUpperCase());
    }

    public PayslipResponse generatePayslip(String employeeId) {
        Employee employee = findEmployee(employeeId);
        PayslipRecord record = new PayslipRecord(employee.getEmployeeId(), employee.getName(), employee.getDepartment(), employee.getNetSalary());
        payslipRecordRepository.save(record);
        return new PayslipResponse(EmployeeResponse.from(employee), record.getGeneratedAt());
    }

    public List<PayslipRecordResponse> payslipHistory() {
        return payslipRecordRepository.findTop20ByOrderByGeneratedAtDesc().stream()
                .map(PayslipRecordResponse::from)
                .toList();
    }

    public void clearPayslipHistory() {
        payslipRecordRepository.deleteAll();
    }

    public DashboardSummary dashboardSummary() {
        return dashboardSummary(true);
    }

    public DashboardSummary dashboardSummary(boolean includeSalary) {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> activeEmployees = employees.stream()
                .filter(employee -> employee.getStatus() == EmployeeStatus.ACTIVE)
                .toList();
        double totalPayout = activeEmployees.stream().mapToDouble(Employee::getNetSalary).sum();
        double averageSalary = activeEmployees.isEmpty() ? 0 : totalPayout / activeEmployees.size();

        EmployeeResponse highestPaid = includeSalary ? employees.stream()
                .max(Comparator.comparingDouble(Employee::getNetSalary))
                .map(EmployeeResponse::from)
                .orElse(null) : null;
        EmployeeResponse lowestPaid = includeSalary ? employees.stream()
                .min(Comparator.comparingDouble(Employee::getNetSalary))
                .map(EmployeeResponse::from)
                .orElse(null) : null;

        List<EmployeeResponse> topEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeId))
                .limit(5)
                .map(employee -> EmployeeResponse.from(employee, includeSalary))
                .toList();

        return new DashboardSummary(
                employees.size(),
                employees.stream().map(Employee::getDepartment).distinct().count(),
                includeSalary ? totalPayout : 0,
                includeSalary ? averageSalary : 0,
                highestPaid,
                lowestPaid,
                topEmployees,
                includeSalary ? departmentReports() : List.of(),
                includeSalary ? payslipHistory() : List.of()
        );
    }

    public List<DepartmentReport> departmentReports() {
        Map<String, List<Employee>> departments = employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.toList()));

        return departments.entrySet().stream()
                .map(entry -> {
                    double total = entry.getValue().stream().mapToDouble(Employee::getNetSalary).sum();
                    long count = entry.getValue().size();
                    return new DepartmentReport(entry.getKey(), count, total, count == 0 ? 0 : total / count);
                })
                .toList();
    }

    private Employee findEmployee(String employeeId) {
        return employeeRepository.findById(employeeId.toUpperCase())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    private String nextEmployeeId() {
        int next = employeeRepository.findAll().stream()
                .map(Employee::getEmployeeId)
                .map(id -> id.replace("EMP", ""))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(1000) + 1;
        return "EMP" + next;
    }

    private EmployeeStatus safeStatus(EmployeeStatus status) {
        return status == null ? EmployeeStatus.ACTIVE : status;
    }
}
