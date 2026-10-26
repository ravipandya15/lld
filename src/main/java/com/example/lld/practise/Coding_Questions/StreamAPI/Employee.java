package com.example.lld.practise.Coding_Questions.StreamAPI;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Employee {
    private int empId;
    private String empName;
    private double salary;
    private City ciy;
    private int age;

    public Employee(int empId, String empName, double salary, City ciy) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.ciy = ciy;
    }

    public Employee(int empId, String empName, int age) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
    }
}
