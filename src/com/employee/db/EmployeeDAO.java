// This class will implement CRUD operations

package com.employee.db;

import com.employee.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public boolean createEmployee(Employee emp){
        String sql= "INSERT INTO employees (name, department, salary) VALUES (?, ? , ?)";

        try (Connection conn= ConnectionManager.getConnection();
        PreparedStatement pstmt= conn. prepareStatement(sql)) {

        pstmt.setString(1, emp.getName());
        pstmt.setString(2, emp.getDepartment());
        pstmt.setDouble(3, emp.getSalary());

        return pstmt.executeUpdate() > 0;
        }
        catch (SQLException e){
            System.err.println("Error creating employee: " + e.getMessage());
        return false;
        }
    }


    public Employee[] getAllEmployees(){
        List<Employee> employees= new ArrayList<>();
        String sql= "SELECT * FROM employees";

        try(Connection conn= ConnectionManager.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql)) {

            while(rs.next()) {
                Employee emp= new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
                employees.add(emp);
            }
        }
        catch (SQLException e){
            System.err.println("Error retrieving employees: " + e.getMessage());
        }
        return employees.toArray(new Employee[0]);
    }

    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt=conn.prepareStatement(sql)) {

            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getDepartment());
            pstmt.setDouble(3, emp.getSalary());
            pstmt.setInt(4, emp.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection conn=ConnectionManager.getConnection();
             PreparedStatement pstmt=conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
}
