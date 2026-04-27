import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PayrollManager {
    private final HashMap<String, Employee> employees = new HashMap<>();
    private int nextEmployeeNumber = 1006;

    public PayrollManager() {
        preloadSampleEmployees();
    }

    private void preloadSampleEmployees() {
        employees.put("EMP1001", new Employee("EMP1001", "Arjun Mehta", "Engineering", "Software Engineer", 55000));
        employees.put("EMP1002", new Employee("EMP1002", "Sneha Reddy", "HR", "HR Manager", 48000));
        employees.put("EMP1003", new Employee("EMP1003", "Kiran Rao", "Finance", "Accountant", 42000));
        employees.put("EMP1004", new Employee("EMP1004", "Divya Nair", "Engineering", "Senior Developer", 70000));
        employees.put("EMP1005", new Employee("EMP1005", "Rahul Sharma", "Marketing", "Marketing Lead", 38000));
    }

    public Employee addEmployee(String name, String department, String designation, double basicSalary) {
        String employeeId = "EMP" + nextEmployeeNumber++;
        Employee employee = new Employee(employeeId, name, department, designation, basicSalary);
        employees.put(employeeId, employee);
        return employee;
    }

    public boolean removeEmployee(String employeeId) {
        return employees.remove(employeeId.toUpperCase()) != null;
    }

    public boolean updateEmployee(String employeeId, String name, String department, String designation, double basicSalary) {
        Employee employee = employees.get(employeeId.toUpperCase());
        if (employee == null) {
            return false;
        }

        employee.setName(name);
        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setBasicSalary(basicSalary);
        return true;
    }

    public Optional<Employee> findEmployee(String employeeId) {
        return Optional.ofNullable(employees.get(employeeId.toUpperCase()));
    }

    public String generatePayslip(String employeeId) {
        return findEmployee(employeeId)
                .map(Employee::displayPayslip)
                .orElse("Employee not found for ID: " + employeeId);
    }

    public List<Employee> listAllEmployees() {
        return employees.values().stream()
                .sorted(Comparator.comparing(Employee::getEmployeeId))
                .collect(Collectors.toList());
    }

    public Map<String, Double> departmentReport() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Employee::getNetSalary)));
    }

    public String salaryReport() {
        if (employees.isEmpty()) {
            return "No employees available for salary report.";
        }

        Employee highestPaid = employees.values().stream()
                .max(Comparator.comparingDouble(Employee::getNetSalary))
                .orElseThrow();
        Employee lowestPaid = employees.values().stream()
                .min(Comparator.comparingDouble(Employee::getNetSalary))
                .orElseThrow();
        double totalPayout = employees.values().stream()
                .mapToDouble(Employee::getNetSalary)
                .sum();
        double averageSalary = totalPayout / employees.size();

        return "\nSalary Report\n"
                + "-------------\n"
                + "Highest Paid Employee : " + highestPaid.getName() + " (" + Employee.formatCurrency(highestPaid.getNetSalary()) + ")\n"
                + "Lowest Paid Employee  : " + lowestPaid.getName() + " (" + Employee.formatCurrency(lowestPaid.getNetSalary()) + ")\n"
                + "Total Monthly Payout  : " + Employee.formatCurrency(totalPayout) + "\n"
                + "Average Net Salary    : " + Employee.formatCurrency(averageSalary) + "\n";
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public List<String> getDepartments() {
        return new ArrayList<>(employees.values().stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toCollection(java.util.TreeSet::new)));
    }
}
