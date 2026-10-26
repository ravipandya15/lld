package com.example.lld.practise.designPatterns.ProxyDesignPattern;

public class EmployeeDaoImpl implements IEmployeeDao {
    @Override
    public void createEmployee(String clientId, Employee employee) throws Exception {
        System.out.println("Creating a new Employee");
    }

    @Override
    public void deleteEmployee(String clientId, int employeeId) throws Exception {
        System.out.println("Deleting an employee with Id: " + employeeId);
    }

    @Override
    public Employee getEmployeeById(String clientId, int employeeId) throws Exception {
        System.out.println("Get Employee by Id: " + employeeId);
        return new Employee();
    }
}
