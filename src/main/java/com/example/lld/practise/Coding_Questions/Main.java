package com.example.lld.practise.Coding_Questions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* INTERVIEW CODE IN TEXT

class Employee {
    String employeName;
    Date joiningDate;
    int employeeCode;

    private Employee(String employeeName, Date joiningDate, employeeCode) {
        this.employeeName = employeeName;
        Date joiningDate = joiningDate;
        this.employeeCode = employeeCode;
    }

    static Employee employeeInstance = new Employee("Ravi", new Date("30/08/2024", 123));

    public static getInstance() {
        return employeeInstance;
    }
}

public class Employee {
    private final String employeName;
    private final Date joiningDate;
    private final int employeeCode;

    public Employee(String employeeName, Date joiningDate, employeeCode) {
        this.employeeName = employeeName;
        Date joiningDate = joiningDate;
        this.employeeCode = employeeCode;
    }

    public Date getJoiningDate() {
        return this.joiningDate;
    }
}

emp.getJoiningDate().setTime();

        Employee employee = new Employee();
        employee.setName("Test_1");
        employee.setAge(30);
        updateEmp(employee);
        System.out.println(employee.getName());
        }
private static void updateEmp(Employee employee_1) {
        Employee emp_2 = new Employee();
        emp_2.setName("Test_2");
        //employee_1 = emp_2;
        employee_1.setName("Test_2");
        }


        I/P: 1,2,3,6,7,0,9,7
        0,1,2,3,4,5,6,7

        O/P: [[1,2,3], [6,7]]

        Books

        AutherName,
        Copies
        check out books

        Admin
        see who checkout books
        books which are dued


class Book {
    String bookId;
    String bookName;
    String autherId;
}

class Book_Data {
    String bookDataId;
    String bookId;
    int copies;

    CONSTRAINS copies >= 0;
}

class BookPurchase {
    String bookId;
    String userId;
    Date issueDate;
}

class Auther {
    String autherId;
    String autherName;
    String specialization;
}

1 -> 10
        2 -> 5

        Table [Book]

        1 Mahabharat xyz
        2 Ramayan abc

        BookData [table]

        1 1 2
        2 2 2

        BookPurchase [table]
        1	102		29/08/2024
        1	101		30/08/2024

        select * from Book b
        Inner Join BookData bd
        ON b.bookId = bd.bookId
        where b.bookName like 'bharat';



 INTERVIEW CODE IN TEXT
 */


public class Main {
    private static List<List<Integer>> getIncrementSubArrays(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int count = 0;
        int n = nums.length;
        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i < n && nums[i] == 1 + nums[i-1]) {
                count++;
                temp.add(nums[i-1]);
                continue;
            }
            if (count > 0) {
                count = 0;
                temp.add(nums[i-1]);
                ans.add(temp);
                temp = new ArrayList<>();
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Ravi", new Date("28/05/2024"), 123);
        emp.getJoiningDate().setTime(123);

        Date date = new Date(); // Current date
        Employee emp1 = new Employee("Ravi", date, 123);

        // Print the joining date
        System.out.println("Joining Date: " + emp1.getJoiningDate());

        // Attempt to modify the date
        Date empDate = emp1.getJoiningDate();
        empDate.setTime(123456789L); // This will not affect the original joiningDate in Employee

        // Print the joining date again to show it hasn't changed
        System.out.println("Joining Date after modification attempt: " + emp1.getJoiningDate());
        // OTHER OPTION IS LOCALDATE CAN ALSO BE USED.

        int[] nums = {1,2,3,6,7,0,9,7, 8, 9};
        List<List<Integer>> result = getIncrementSubArrays(nums);
        for (List<Integer> t: result) {
            for (int value: t) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }
    }
}
