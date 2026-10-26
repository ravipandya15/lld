package com.example.lld.practise.EmployeeAudit;

import lombok.ToString;

@ToString
public class Employee {
    int id;
    Division division;

    public void setId(int id) {
        this.id = id;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    String phoneNumber;

    public int getId() {
        return id;
    }

    public Division getDivision() {
        return division;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    boolean isActive;

    public Employee(int id, Division division, String phoneNumber, boolean isActive) {
        this.id = id;
        this.division = division;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Employee(int id, Division division, String phoneNumber) {
        this.id = id;
        this.division = division;
        this.phoneNumber = phoneNumber;
        this.isActive = true;
    }
}
