package com.example.lld.practise.Coding_Questions.StreamAPI;

import java.util.Collections;
import java.util.List;

public class NthHighestSalary {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Ravi", 500000.0, new City("Surat", 100)),
                new Employee(2, "Raj", 100000.0, new City("Surat", 100)),
                new Employee(3, "Kaushik", 300000.0, new City("Surat", 100)),
                new Employee(4, "Mohit", 200000.0, new City("Surat", 100)),
                new Employee(5, "Prabhas", 200000.0, new City("Surat", 100)),
                new Employee(6, "Sruti", 400000.0, new City("Surat", 100))
        );

        System.out.println("Nth highest salary: " + nThHighestSalary(3, employees));
        System.out.println("Nth highest salary: " + nThHighestSalary(6, employees));
        System.out.println("Nth highest salary: " + nThHighestSalary(1, employees));
    }

    private static double nThHighestSalary(int n, List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Collections.reverseOrder())
                .skip(n-1)
                .findFirst()
                .orElse(0.0);

        /*
            SELECT salary
            FROM (
                    SELECT salary,
                            DENSE_RANK() over (ORDER BY salary DESC) as rank
                    FROM Employee
                )
            WHERE rank = 3;
         */
    }
}

/*
        Employee
        --------
    Alice    100000
    Bob       90000
    Charlie   90000
    David     80000
    Eve       70000

    100000 → 1st
     90000 → 2nd
     80000 → 3rd
     70000 → 4th

     Oracle

        SELECT salary
        FROM (
            SELECT salary,
                   DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
            FROM employee
        )
        WHERE rnk = 3;
 */
