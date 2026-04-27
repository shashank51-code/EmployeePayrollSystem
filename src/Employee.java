public class Employee {
    private final String employeeId;
    private String name;
    private String department;
    private String designation;
    private double basicSalary;

    public Employee(String employeeId, String name, String department, String designation, double basicSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary;
    }

    public String getEmployeeId() {
        return employeeId;
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

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
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

    public String displayPayslip() {
        String line = "+--------------------------------------------------+";
        return "\n" + line + "\n"
                + "|                 EMPLOYEE PAYSLIP                |\n"
                + line + "\n"
                + String.format("| Employee ID    : %-30s |\n", employeeId)
                + String.format("| Name           : %-30s |\n", name)
                + String.format("| Department     : %-30s |\n", department)
                + String.format("| Designation    : %-30s |\n", designation)
                + line + "\n"
                + String.format("| Basic Salary   : %-30s |\n", formatCurrency(basicSalary))
                + String.format("| HRA (20%%)      : %-30s |\n", formatCurrency(getHRA()))
                + String.format("| DA (10%%)       : %-30s |\n", formatCurrency(getDA()))
                + String.format("| TA (5%%)        : %-30s |\n", formatCurrency(getTA()))
                + String.format("| Gross Salary   : %-30s |\n", formatCurrency(getGrossSalary()))
                + line + "\n"
                + String.format("| PF (12%%)       : %-30s |\n", formatCurrency(getPF()))
                + String.format("| Tax (8%%)       : %-30s |\n", formatCurrency(getTax()))
                + String.format("| Insurance (2%%) : %-30s |\n", formatCurrency(getInsurance()))
                + String.format("| Deductions     : %-30s |\n", formatCurrency(getTotalDeductions()))
                + line + "\n"
                + String.format("| Net Salary     : %-30s |\n", formatCurrency(getNetSalary()))
                + line + "\n";
    }

    public static String formatCurrency(double amount) {
        return String.format("Rs. %,.2f", amount);
    }

    @Override
    public String toString() {
        return String.format("%-8s %-18s %-15s %-22s %12s",
                employeeId, name, department, designation, formatCurrency(basicSalary));
    }
}
