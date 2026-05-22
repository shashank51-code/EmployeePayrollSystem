# Employee Payroll Management System

A Spring Boot full-stack Employee Payroll Management System with a responsive HTML, CSS, and JavaScript web UI, REST APIs, Spring Security login, and PostgreSQL persistence.

The deployed app is available at:

```text
https://employeepayrollsystem-production.up.railway.app/login.html
```

The app also runs locally at:

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

## How To Run

From the project folder:

```powershell
.\mvnw.cmd spring-boot:run
```

Open:

```text
http://localhost:8080/login.html
```

Railway deployment:

```text
https://employeepayrollsystem-production.up.railway.app/login.html
```

## Database

The application uses PostgreSQL. Example local connection:

```text
http://localhost:5432 (PostgreSQL server)
```

Recommended approach: create a dedicated database and user, and set credentials via environment variables.

Example psql commands (run as postgres superuser):

```sql
CREATE DATABASE payrolldb;
CREATE USER payroll WITH ENCRYPTED PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE payrolldb TO payroll;
```

Then set environment variables before running the app:

PowerShell:

```powershell
$env:JDBC_DATABASE_URL = 'jdbc:postgresql://localhost:5432/payrolldb'
$env:JDBC_DATABASE_USERNAME = 'payroll'
$env:JDBC_DATABASE_PASSWORD = 'your_password'
.\\mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
export JDBC_DATABASE_URL='jdbc:postgresql://localhost:5432/payrolldb'
export JDBC_DATABASE_USERNAME='payroll'
export JDBC_DATABASE_PASSWORD='your_password'
./mvnw spring-boot:run
```

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
