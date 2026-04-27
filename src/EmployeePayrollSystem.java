import java.util.Map;
import java.util.Scanner;

public class EmployeePayrollSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PayrollManager payrollManager = new PayrollManager();

    public static void main(String[] args) {
        boolean running = true;
        printHeader();

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> removeEmployee();
                case 3 -> updateEmployee();
                case 4 -> generatePayslip();
                case 5 -> listAllEmployees();
                case 6 -> departmentReport();
                case 7 -> salaryReport();
                case 8 -> {
                    running = false;
                    System.out.println("Thank you for using Employee Payroll Management System.");
                }
                default -> System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("====================================================");
        System.out.println("        Employee Payroll Management System");
        System.out.println("====================================================");
    }

    private static void printMenu() {
        System.out.println("\n1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Generate Payslip");
        System.out.println("5. List All Employees");
        System.out.println("6. Department Report");
        System.out.println("7. Salary Report");
        System.out.println("8. Exit");
    }

    private static void addEmployee() {
        System.out.println("\nAdd Employee");
        String name = readNonEmpty("Full Name: ");
        String department = readNonEmpty("Department: ");
        String designation = readNonEmpty("Designation: ");
        double basicSalary = readPositiveDouble("Basic Salary: ");

        Employee employee = payrollManager.addEmployee(name, department, designation, basicSalary);
        System.out.println("Employee added successfully with ID: " + employee.getEmployeeId());
    }

    private static void removeEmployee() {
        String employeeId = readNonEmpty("Enter Employee ID to remove: ");
        if (payrollManager.removeEmployee(employeeId)) {
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployee() {
        String employeeId = readNonEmpty("Enter Employee ID to update: ");
        if (payrollManager.findEmployee(employeeId).isEmpty()) {
            System.out.println("Employee not found.");
            return;
        }

        String name = readNonEmpty("Updated Full Name: ");
        String department = readNonEmpty("Updated Department: ");
        String designation = readNonEmpty("Updated Designation: ");
        double basicSalary = readPositiveDouble("Updated Basic Salary: ");

        payrollManager.updateEmployee(employeeId, name, department, designation, basicSalary);
        System.out.println("Employee updated successfully.");
    }

    private static void generatePayslip() {
        String employeeId = readNonEmpty("Enter Employee ID: ");
        System.out.println(payrollManager.generatePayslip(employeeId));
    }

    private static void listAllEmployees() {
        System.out.println("\nAll Employees");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-8s %-18s %-15s %-22s %12s%n", "Emp ID", "Name", "Department", "Designation", "Basic Salary");
        System.out.println("-------------------------------------------------------------------------------");
        payrollManager.listAllEmployees().forEach(System.out::println);
    }

    private static void departmentReport() {
        System.out.println("\nDepartment-wise Net Salary Breakdown");
        System.out.println("------------------------------------");
        for (Map.Entry<String, Double> entry : payrollManager.departmentReport().entrySet()) {
            System.out.printf("%-18s %15s%n", entry.getKey(), Employee.formatCurrency(entry.getValue()));
        }
    }

    private static void salaryReport() {
        System.out.println(payrollManager.salaryReport());
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException error) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("Amount must be greater than zero.");
            } catch (NumberFormatException error) {
                System.out.println("Please enter a valid salary amount.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }
}
