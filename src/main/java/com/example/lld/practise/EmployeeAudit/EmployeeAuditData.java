package com.example.lld.practise.EmployeeAudit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EmployeeAuditData {
    public static void main(String[] args) {
        List<Employee> presentQuartorEmployee = new ArrayList<>();
        presentQuartorEmployee.add(new Employee(1, Division.A, "1"));
        presentQuartorEmployee.add(new Employee(2, Division.B, "1"));
        presentQuartorEmployee.add(new Employee(3, Division.C, "1"));
        presentQuartorEmployee.add(new Employee(4, Division.A, "1"));
        presentQuartorEmployee.add(new Employee(5, Division.B, "1"));
        presentQuartorEmployee.add(new Employee(6, Division.C, "1"));
        presentQuartorEmployee.add(new Employee(7, Division.A, "1"));
        presentQuartorEmployee.add(new Employee(8, Division.B, "1"));
        presentQuartorEmployee.add(new Employee(9, Division.C, "1"));
        presentQuartorEmployee.add(new Employee(10, Division.A, "1"));

        List<Employee> masterEmployeeList = new ArrayList<>();
        masterEmployeeList.add(new Employee(1, Division.A, "1", false));
        masterEmployeeList.add(new Employee(2, Division.B, "1", true));
        masterEmployeeList.add(new Employee(3, Division.A, "1", true));
        masterEmployeeList.add(new Employee(4, Division.A, "2", true));
        masterEmployeeList.add(new Employee(5, Division.C, "1", true));
        masterEmployeeList.add(new Employee(6, Division.C, "1", true));
        masterEmployeeList.add(new Employee(76, Division.A, "1", true));
        masterEmployeeList.add(new Employee(8, Division.A, "2", true));
        masterEmployeeList.add(new Employee(91, Division.C, "1", true));
        masterEmployeeList.add(new Employee(10, Division.C, "1", true));

        modifyList(presentQuartorEmployee, masterEmployeeList);

        for (Employee e : masterEmployeeList) {
            System.out.println(e.toString());
        }

        System.out.println("Completed");
    }

    public static void modifyList(List<Employee> presentQuartorEmployee, List<Employee> masterEmployeeList) {
        List<EmployeeAudit> auditEmployee = new ArrayList<>();
        Map<Integer, Employee> present = new HashMap<>();
        for (Employee e : presentQuartorEmployee) {
            present.put(e.getId(), e);
        }

        for (Employee e : masterEmployeeList) {
            if (present.containsKey(e.getId())) {
                Employee presentEmployee = present.get(e.getId());
                e.setDivision(presentEmployee.getDivision());
                e.setPhoneNumber(presentEmployee.getPhoneNumber());
                e.setActive(true);
            } else {

                e.setActive(false);
            }
            present.remove(e.getId());
        }

        for (Map.Entry<Integer, Employee> remainingEmployee : present.entrySet()) {
            masterEmployeeList.add(remainingEmployee.getValue());
            auditEmployee.add(new EmployeeAudit(remainingEmployee.getValue(), "ADD", new Timestamp(123456L)));
        }
    }
}
