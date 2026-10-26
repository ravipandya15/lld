package com.example.lld.practise.designPatterns.ProxyDesignPattern;

public interface IEmployeeDao {
    void createEmployee(String clientId, Employee employee) throws Exception;
    void deleteEmployee(String clientId, int employeeId) throws Exception;
    Employee getEmployeeById(String clientId, int employeeId) throws Exception;
}
