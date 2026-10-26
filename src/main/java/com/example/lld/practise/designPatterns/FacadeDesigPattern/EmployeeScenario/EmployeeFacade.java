package com.example.lld.practise.designPatterns.FacadeDesigPattern.EmployeeScenario;

public class EmployeeFacade {
    private EmployeeDao employeeDao;

    EmployeeFacade(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void insert() {
        employeeDao.insert();
    }

    public Employee getEmployeeDetails(int empId) {
        return employeeDao.getEmployeeDetails(empId);
    }
}
