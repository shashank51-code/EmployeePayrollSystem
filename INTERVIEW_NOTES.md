# Interview Notes - Employee Payroll Management System

## How To Explain The Project

This is a Spring Boot full-stack Employee Payroll Management System. When the Java application runs, it starts an embedded Tomcat server and serves the website at `http://localhost:8099`.

The user works from the browser. The frontend uses JavaScript `fetch` calls to communicate with Spring Boot REST APIs, and the backend stores employee and payslip data in an H2 database using Spring Data JPA.

## Architecture

```text
Browser UI
   |
   v
JavaScript Fetch API
   |
   v
Spring Boot REST Controller
   |
   v
PayrollService business logic
   |
   v
Spring Data JPA Repository
   |
   v
H2 file database
```

## Spring Boot Features Used

- Embedded Tomcat server
- Spring MVC REST controllers
- Spring Data JPA repositories
- H2 database
- Spring Security form login
- BCrypt password encoder
- Role-based authorization
- Bean validation using Jakarta Validation
- CommandLineRunner sample data seeding
- Static resource hosting for the HTML/CSS/JS website
- ApplicationReadyEvent browser launcher

## Security Explanation

I added Spring Security so users must log in before using the payroll system.

- `admin/admin123` has the `ADMIN` role.
- `hr/hr123` has the `HR` role.
- Admin can delete employees, clear payslip history, and access the H2 console.
- HR can add/edit employees, generate payslips, export CSV, and view reports.
- Passwords are encoded with BCrypt in the security configuration.

## Database Explanation

I used H2 file database for easy student setup. It behaves like a database but does not require installing MySQL.

The database file is created under:

```text
data/payroll-db
```

In production, I would replace H2 with MySQL or PostgreSQL and store users in a database table instead of in-memory demo users.

## Business Logic Explanation

The `Employee` entity contains the salary calculation methods:

- `getHRA()` = 20% of basic salary
- `getDA()` = 10% of basic salary
- `getTA()` = 5% of basic salary
- `getGrossSalary()` = Basic + HRA + DA + TA
- `getPF()` = 12% of basic salary
- `getTax()` = 8% of basic salary
- `getInsurance()` = 2% of basic salary
- `getNetSalary()` = Gross Salary - Total Deductions

The `PayrollService` class handles application business logic:

- Add employee
- Update employee
- Delete employee
- Generate payslip
- Save payslip history
- Build dashboard metrics
- Build department-wise reports

## Resume Bullets

- Built a Spring Boot Employee Payroll Management System with REST APIs, Spring Data JPA, H2 database persistence, and a professional browser-based UI.
- Implemented Java OOP salary calculation logic for HRA, DA, TA, PF, tax, insurance, gross salary, deductions, and net salary.
- Added Spring Security form login with BCrypt password encoding and role-based access for Admin and HR users.
- Developed real-time payroll dashboard features including employee CRUD, printable payslips, payslip history, CSV export, and department-wise salary reporting.
