// This class will handle the database

package com.employee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL= "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER="anshu";
    private static final String PASSWORD="637762";



public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (SQLException e){
            System.err.println("Connection error: " + e.getMessage());
            return null;
        }
    }
    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            }
            catch (SQLException e){
                System.err.println("Error closing connection: "+ e.getMessage());
            }
        }
    }
}
