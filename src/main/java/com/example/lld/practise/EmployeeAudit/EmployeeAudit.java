package com.example.lld.practise.EmployeeAudit;

import java.sql.Timestamp;

public class EmployeeAudit {

    Employee employee;
    String operation;
    Timestamp currentTimeStamp;

    public EmployeeAudit(Employee employee, String operation, Timestamp currentTimeStamp) {
        this.employee = employee;
        this.operation = operation;
        this.currentTimeStamp = currentTimeStamp;
    }
}
