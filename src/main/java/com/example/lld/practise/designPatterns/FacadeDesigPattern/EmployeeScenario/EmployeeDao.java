package com.example.lld.practise.designPatterns.FacadeDesigPattern.EmployeeScenario;

public class EmployeeDao {

    public void insert() {
        // insert into employee table
    }

    public void updateEmployeeName() {
        // updating employee Name
    }

    public Employee getEmployeeDetails(String employeeId) {
        return new Employee();
    }

    public Employee getEmployeeDetails(int EmployeeId) {
        return new Employee();
    }
}
