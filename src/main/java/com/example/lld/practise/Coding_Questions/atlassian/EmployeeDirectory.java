package com.example.lld.practise.Coding_Questions.atlassian;

import java.util.HashSet;
import java.util.Set;

/*
Imagine you are the team that maintains the Atlassian employee directory.
At Atlassian - there are multiple groups, and each can have one or more groups. Every employee is part of a group.
You are tasked with designing a system that could find the closest common parent group  given a target set of employees in the organization.

                            Atlassian

        Engineering      Market                   Finance
                        E3  E6
        Backend team    FrontEnd team       Payroll     HR Team

        E1              E4                  E6          G1  G2  E10
        E2              E5                  E7          E8  E9
        E3              E6

        Result getClosestCommonGroup(Set<Employee> employees, Node root)

        Result
        int count;
        String department/group


        dfs, E = Employees, D = Departments
        TC -> O(E + D)
        SC -> O (E + D)

 */

class Node {
    String value;
    Type type;
    Set<Node> childs;

    public Node(String value, Type type) {
        this.value = value;
        this.type = type;
        childs = new HashSet<>();
    }
}

enum Type {
    DEPARTMENT, EMPLOYEE
}

class Result {
    int count;
    String value;

    public Result() {
        this.count = 0;
        this.value = "";
    }

    public Result(int count, String value) {
        this.count = count;
        this.value = value;
    }
}
public class EmployeeDirectory {

    public Result getClosestCommonGroup(Set<String> employees, Node root) {
        return dfs(employees, root);
    }

    private Result dfs(Set<String> employees, Node node) {
        // base condition

        if (node.type.name().isBlank()) {
            return new Result(1, "");
        }

        if (node.value != null && !node.value.isBlank() && employees.contains(node.value)) {
            return new Result(1, "");
        }

        if (node.type == Type.EMPLOYEE) {
            return new Result(0, "");
        }

        if (node.type == Type.DEPARTMENT && node.childs.isEmpty()) {
            return new Result(0, "");
        }

        Result result = new Result();

        for (Node child : node.childs) {

            Result childResult = dfs(employees, child);

            if (childResult.value != null && !childResult.value.isBlank()) {
                return childResult;
            }

            result.count += childResult.count;
        }

        if (node.type == Type.DEPARTMENT && result.count == employees.size()) {
            result.value = node.value;
        }

        return result;

    }

    static void main() {
        // Tree
        EmployeeDirectory employeeDirectory = new EmployeeDirectory();
        Node root = new Node("Atlassian", Type.DEPARTMENT);

        Node engineering = new Node("Engineering", Type.DEPARTMENT);
        Node finance = new Node("Finance", Type.DEPARTMENT);
        Node payroll = new Node("Payroll", Type.DEPARTMENT);

        Node backend = new Node("Backend", Type.DEPARTMENT);
        Node frontEnd = new Node("FrontEnd", Type.DEPARTMENT);



        Node e1 = new Node("emp1", Type.EMPLOYEE);
        Node e2 = new Node("emp2", Type.EMPLOYEE);
        Node e3 = new Node("emp3", Type.EMPLOYEE);

        Node e4 = new Node("emp4", Type.EMPLOYEE);
        Node e5 = new Node("emp5", Type.EMPLOYEE);

        Node e6 = new Node("emp6", Type.EMPLOYEE);
        Node e7 = new Node("emp7", Type.EMPLOYEE);



        backend.childs.add(e1);
        backend.childs.add(e2);
        backend.childs.add(e3);

        frontEnd.childs.add(e4);
        frontEnd.childs.add(e5);

        payroll.childs.add(e6);
        payroll.childs.add(e7);

        engineering.childs.add(frontEnd);
        engineering.childs.add(backend);

        finance.childs.add(payroll);

        root.childs.add(engineering);
        root.childs.add(finance);

        Result result1 = employeeDirectory.getClosestCommonGroup(Set.of("emp1", "emp2", "emp6"), root);
        Result result2 = employeeDirectory.getClosestCommonGroup(Set.of("emp1", "emp2", "emp4"), root);

        Result result3 = employeeDirectory.getClosestCommonGroup(Set.of("emp1", "emp2", "emp10"), root);
        Result result4 = employeeDirectory.getClosestCommonGroup(Set.of("emp6", "emp7"), root);

        System.out.println(result1 != null && result1.value.isBlank() ? "No Common Department" : result1.value);
        System.out.println(result2 != null && result2.value.isBlank() ? "No Common Department" : result2.value);
        System.out.println(result3 != null && result3.value.isBlank() ? "No Common Department" : result3.value);
        System.out.println(result4.value);
    }

}
