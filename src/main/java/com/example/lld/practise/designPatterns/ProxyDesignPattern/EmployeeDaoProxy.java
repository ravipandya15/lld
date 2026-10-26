package com.example.lld.practise.designPatterns.ProxyDesignPattern;

public class EmployeeDaoProxy implements IEmployeeDao {

    IEmployeeDao employeeDao;

    EmployeeDaoProxy() {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void createEmployee(String clientId, Employee employee) throws Exception {
        if (clientId.equalsIgnoreCase("ADMIN")) {
            employeeDao.createEmployee(clientId, employee);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public void deleteEmployee(String clientId, int employeeId) throws Exception {
        if (clientId.equalsIgnoreCase("ADMIN")) {
            employeeDao.deleteEmployee(clientId, employeeId);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public Employee getEmployeeById(String clientId, int employeeId) throws Exception {
        if (clientId.equalsIgnoreCase("ADMIN") || clientId.equalsIgnoreCase("USER")) {
            return employeeDao.getEmployeeById(clientId, employeeId);
        }
        throw new Exception("Access Denied");
    }
}
