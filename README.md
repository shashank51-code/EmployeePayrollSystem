# Employee Payroll Management System

A Spring Boot full-stack Employee Payroll Management System with a responsive HTML, CSS, and JavaScript web UI, REST APIs, Spring Security login, and PostgreSQL persistence.


The app runs locally at:

```text
http://localhost:8080/login.html
```

## Features

- Spring Boot embedded Tomcat server
- Spring Security form login
- Admin and employee role-based access
- Employee signup using Employee ID and password
- New employee signup stores employee profile data in the PostgreSQL database
- Admin can view all employees, assign salary, update status, delete employees, generate payslips, and view reports
- Employees can view other employee profile details without salary information
- Employees can generate only their own payslip
- Employees can update their own profile details
- Salary privacy protection for employee users
- Uses PostgreSQL for persistence; configure connection in `src/main/resources/application.properties`
- Spring Data JPA entities and repositories
- REST APIs for employees, profile, dashboard, reports, and payslips
- Clean responsive dashboard
- Department-wise salary report for admin
- Highest paid employee, lowest paid employee, and total monthly payout summary for admin
- Pre-loaded sample employee data

## Login Details

### Admin

| Employee ID | Password | Access |
| --- | --- | --- |
| `admin` | `admin123` | Full admin access |

### Employee

Employees must create an account from the signup page using:

- Employee ID
- Name
- Department
- Designation
- Password

After signup, employees log in using their Employee ID and password.

## Role Access

| Feature | Admin | Employee |
| --- | --- | --- |
| View employee list | Yes | Yes, salary hidden |
| Add employee | Yes | No |
| Edit employee salary/status | Yes | No |
| Delete employee | Yes | No |
| View reports | Yes | No |
| Generate any employee payslip | Yes | No |
| Generate own payslip | Yes | Yes |
| Update own profile | Fixed admin account | Yes |

## Salary Structure

| Component | Calculation |
| --- | --- |
| Basic Salary | Assigned by admin |
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
- PostgreSQL
- HTML5
- CSS3
- JavaScript Fetch API
- Maven Wrapper
