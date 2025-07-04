// demonstrates the usage of an application

package com.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.employee.db.EmployeeDAO;
import com.employee.model.Employee;

public class Main {
    private static EmployeeDAO dao = new EmployeeDAO();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            String choiceStr = readLine();

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        viewAllEmployees();
                        break;
                    case 3:
                        updateEmployee();
                        break;
                    case 4:
                        deleteEmployee();
                        break;
                    case 5:
                        System.out.println("Thank you for using Employee Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return null;
        }
    }

    private static void addEmployee() {
        String name, department;
        double salary = -1;
        System.out.print("Enter employee name: ");
        name = readLine();
        System.out.print("Enter department: ");
        department = readLine();

        // Salary input validation
        while (true) {
            System.out.print("Enter salary: ");
            String salaryStr = readLine();
            try {
                salary = Double.parseDouble(salaryStr);
                if (salary < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid salary. Please enter a valid number for salary.");
            }
        }

        Employee emp = new Employee(0, name, department, salary);
        if (dao.createEmployee(emp)) {
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private static void viewAllEmployees() {
        Employee[] employees = dao.getAllEmployees();
        if (employees.length == 0) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("\nList of Employees:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private static void updateEmployee() {
        int id = -1;
        double salary = -1;
        String name, department;

        // Get valid employee ID
        while (true) {
            System.out.print("Enter employee ID to update: ");
            String idStr = readLine();
            try {
                id = Integer.parseInt(idStr);
                if (id < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid ID. Please enter a valid numeric employee ID.");
            }
        }

        System.out.print("Enter new name: ");
        name = readLine();
        System.out.print("Enter new department: ");
        department = readLine();

        // Get valid salary
        while (true) {
            System.out.print("Enter new salary: ");
            String salaryStr = readLine();
            try {
                salary = Double.parseDouble(salaryStr);
                if (salary < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid salary. Please enter a valid number for salary.");
            }
        }

        Employee emp = new Employee(id, name, department, salary);

        if (dao.updateEmployee(emp)) {
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Failed to update employee.");
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee IDs to delete: ");
        String idsStr = readLine();
        if (idsStr == null || idsStr.trim().isEmpty()) {
            System.out.println("No IDs entered.");
            return;
        }
        String[] idParts = idsStr.split(",");
        boolean anyDeleted = false;

        for (String idPart : idParts) {
            try {
                int id = Integer.parseInt(idPart.trim());
                if (dao.deleteEmployee(id)) {
                    System.out.println("Employee with ID " + id + " deleted successfully!");
                    anyDeleted = true;
                } else {
                    System.out.println("Employee with ID " + id + " not found or deletion failed.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid ID: '" + idPart.trim() + "'. Skipping.");
            }
        }
        if (!anyDeleted) {
            System.out.println("No employees were deleted.");
        }
    }
}