// This java file is Data Model that represents an employee entity

package com.employee.model;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(){}

    public Employee(int id, String name, String department, double salary){
        this.id=id;
        this.name=name;
        this.department=department;
        this.salary=salary;
    }

    // Getter methods
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDepartment(){
        return department;
    }
    public double getSalary(){
        return salary;
    }

    // Setter Methods
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }

    @Override
    public String toString(){
        return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
    }
}
