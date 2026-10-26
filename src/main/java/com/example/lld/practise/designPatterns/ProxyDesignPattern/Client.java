package com.example.lld.practise.designPatterns.ProxyDesignPattern;

public class Client {
    public static void main(String[] args) {
        IEmployeeDao employeeDao = new EmployeeDaoProxy();
        try {
            employeeDao.createEmployee("TEMP", new Employee());
            employeeDao.createEmployee("ADMIN", new Employee());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
