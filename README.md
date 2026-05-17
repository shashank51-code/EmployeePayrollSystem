# Employee Payroll Management System

A Spring Boot full-stack Employee Payroll Management System with a professional HTML, CSS, and JavaScript web UI, REST APIs, Spring Security login, and H2 database persistence. Students run the Java Spring Boot application, then work from the browser at `http://localhost:8099`.

## Features

- Spring Boot embedded Tomcat server
- Auto-opens the browser to the payroll website after startup when desktop browsing is supported
- Spring Security form login
- BCrypt encoded in-memory demo users
- Role-based access: `ADMIN` can delete records and access H2 console; `HR` can add, edit, report, and generate payslips
- H2 file database stored under `data/payroll-db`
- Spring Data JPA entities and repositories
- REST APIs for employees, dashboard, reports, payslips, and history
- Clean responsive dashboard with dark navy sidebar and gold accents
- Add, edit, delete, view, search, and department-filter employees
- Live salary preview while entering basic salary
- Payslip generator by Employee ID
- Quick payslip generation directly from the employee table
- Printable payslip with employee details, earnings, deductions, gross salary, total deductions, and highlighted net salary
- Payslip generation history stored in the database
- Department-wise salary breakdown report
- Highest paid employee, lowest paid employee, and total monthly payout summary
- CSV export for payroll reporting
- Pre-loaded sample employee data

## Login

| Role | Username | Password | Access |
| --- | --- | --- | --- |
| Admin | `admin` | `admin123` | Full access, delete employees, clear history, H2 console |
| HR | `hr` | `hr123` | Add/edit employees, generate payslips, view reports |

## Salary Structure

| Component | Calculation |
| --- | --- |
| Basic Salary | User defined |
| HRA | 20% of Basic Salary |
| DA | 10% of Basic Salary |
| TA | 5% of Basic Salary |
| Gross Salary | Basic + HRA + DA + TA |
| PF | 12% of Basic Salary |
| Income Tax | 8% of Basic Salary |
| Insurance | 2% of Basic Salary |
| Total Deductions | PF + Income Tax + Insurance |
| Net Salary | Gross Salary - Total Deductions |

## Technologies Used

- Java 17
- Spring Boot
- Spring MVC REST APIs
- Spring Data JPA
- Spring Security
- H2 Database
- HTML5
- CSS3
- JavaScript Fetch API
- Maven Wrapper

