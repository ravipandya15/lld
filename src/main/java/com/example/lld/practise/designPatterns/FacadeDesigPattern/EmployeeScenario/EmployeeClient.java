package com.example.lld.practise.designPatterns.FacadeDesigPattern.EmployeeScenario;

public class EmployeeClient {

    private EmployeeFacade employeeFacade;

    EmployeeClient(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public Employee getEmployeeDetails(int empId) {
        return employeeFacade.getEmployeeDetails(empId);
    }
}
