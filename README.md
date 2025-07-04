## JDBC-Employee-Database-
A simple, console-based Employee Management System built with Java and JDBC, allowing users to perform CRUD (Create, Read, Update, Delete) operations on employee records stored in a MySQL database.


## Features
- Add new employees
- View all employees
- Update existing employee details
- Delete employees (supports multiple deletions)
- Input validation and error handling

---

## Project Structure
```
JDBC_Employee Database App/
├── src/
│   └── com/employee/
│       ├── Main.java                # Application entry point and menu
│       ├── db/
│       │   ├── ConnectionManager.java   # Handles DB connection
│       │   └── EmployeeDAO.java      # CRUD operations
│       └── model/
│           └── Employee.java         # Employee data model
├── lib/                            # (Optional) External libraries
├── .gitignore                      # Ignored files and folders
├── README.md                       # Project documentation
└── ...
```

---

## Getting Started

### Prerequisites
- Java JDK 8 or higher
- MySQL Server
- JDBC Driver for MySQL (Connector/J)

### Setup Steps
1. **Clone the repository:**
   ```
   git clone https://github.com/anshurs/JDBC-Employee-Database-.git
   cd JDBC_Employee\ Database\ App
   ```
3. **Add MySQL JDBC Driver:**
   - Download the MySQL Connector/J from [MySQL Downloads](https://dev.mysql.com/downloads/connector/j/).
   - Place the JAR file in the `lib/` directory.
   - Add it to the project's classpath.

4. **Configure Database Connection:**
   - Edit `src/com/employee/db/ConnectionManager.java` and update the `URL`, `USER`, and `PASSWORD` constants to match MySQL setup.

---

## Database Setup

1. **Create the Database and Table:**
   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;
   CREATE TABLE employees (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100) NOT NULL,
       department VARCHAR(100) NOT NULL,
       salary DOUBLE NOT NULL
   );
   ```

2. **(Optional) Insert Sample Data:**
   ```sql
   INSERT INTO employees (name, department, salary) VALUES
   ('Alice', 'HR', 50000),
   ('Bob', 'Engineering', 70000);
   ```

---

## Configuration

Edit the following in `ConnectionManager.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

---


## Code Overview

- **Main.java**: Handles user input, displays menu, and calls DAO methods.
- **EmployeeDAO.java**: Implements CRUD operations using JDBC.
- **ConnectionManager.java**: Manages MySQL database connections.
- **Employee.java**: Represents the employee entity (id, name, department, salary).

---

