package com.example.lld.practise.Coding_Questions;

import java.util.Date;

public class Employee {
    private final String employeeName;
    private final Date joiningDate;
    private final int employeeCode;

    public Employee(String employeeName, Date joiningDate, int employeeCode) {
        this.employeeName = employeeName;
//        this.joiningDate = joiningDate;
        this.joiningDate = new Date(joiningDate.getTime());
        // Other option is LocalDate can also be used.
        this.employeeCode = employeeCode;
    }

    public Date getJoiningDate() {
        // Return a defensive copy of the Date object
        return new Date(joiningDate.getTime());

//        return this.joiningDate;
    }
}
