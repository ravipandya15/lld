package com.example.lld.practise.Coding_Questions.OOPs.Interface_With_Abstract_Class;

class Emp {
    int empId;

    void update (Emp e) {
        e.empId = 20;
        System.out.println("Employee id inside update method: " + e.empId);
    }
}


public class Question1 {
    static void main() {
        Emp e = new Emp();
        e.empId = 10;
        e.update(e);
        System.out.println("Employee Id: " + e.empId);
    }
}
