package com.example.lld.practise.CompanySearch;


import java.util.*;

public class CompanyHierarchy {

    public enum Type {
        COMPANY, DEPARTMENT, EMPLOYEE
    }

    public static class Node {
        public String name;
        public Type type;
        public List<Node> children = new ArrayList<>();

        public Node(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public void addChild(Node child) {
            children.add(child);
        }
    }

    static class Result {
        int employeeCount;
        Node department;

        Result(int employeeCount, Node department) {
            this.employeeCount = employeeCount;
            this.department = department;
        }
    }

    private final Node root;
    private final Map<String, Node> nodes = new HashMap<>();

    public CompanyHierarchy(Node root) {
        this.root = root;
        registerNodes(root);
    }

    // Index every node for O(1) average lookup.
    private void registerNodes(Node node) {
        if (nodes.putIfAbsent(node.name, node) != null) {
            throw new IllegalArgumentException(
                    "Duplicate node name: " + node.name
            );
        }

        for (Node child : node.children) {
            registerNodes(child);
        }
    }

    public Node findClosestDepartment(List<String> employeeNames) {
        if (employeeNames == null || employeeNames.isEmpty()) {
            return null;
        }

        Set<String> targets = new HashSet<>(employeeNames);

        // Validate that every target exists and is an employee.
        for (String name : targets) {
            Node node = nodes.get(name);

            if (node == null || node.type != Type.EMPLOYEE) {
                return null;
            }
        }

        Result result = dfs(root, targets);

        // Defensive check: all requested employees must be found.
        if (result.employeeCount != targets.size()) {
            return null;
        }

        return result.department;
    }

    private Result dfs(Node node, Set<String> targets) {
        int count = 0;
        Node answer = null;

        // Count the current employee if it is a target.
        if (node.type == Type.EMPLOYEE
                && targets.contains(node.name)) {
            count = 1;
        }

        // Process all child subtrees.
        for (Node child : node.children) {
            Result childResult = dfs(child, targets);

            count += childResult.employeeCount;

            // Preserve the deepest department found below.
            if (answer == null && childResult.department != null) {
                answer = childResult.department;
            }
        }

        // If no child contains every target, this node may be
        // the closest common department.
        if (answer == null
                && node.type != Type.EMPLOYEE
                && count == targets.size()) {
            answer = node;
        }

        return new Result(count, answer);
    }

    public static void main(String[] args) {
        Node company = new Node("Company", Type.COMPANY);

        Node engineering =
                new Node("Engineering", Type.DEPARTMENT);
        Node hr = new Node("HR", Type.DEPARTMENT);

        Node backend = new Node("Backend", Type.DEPARTMENT);
        Node frontend = new Node("Frontend", Type.DEPARTMENT);
        Node recruitment = new Node("Recruitment", Type.DEPARTMENT);
        Node payroll = new Node("Payroll", Type.DEPARTMENT);

        company.addChild(engineering);
        company.addChild(hr);

        engineering.addChild(backend);
        engineering.addChild(frontend);

        hr.addChild(recruitment);
        hr.addChild(payroll);

        backend.addChild(new Node("Alice", Type.EMPLOYEE));
        backend.addChild(new Node("Bob", Type.EMPLOYEE));
        frontend.addChild(new Node("Charlie", Type.EMPLOYEE));
        recruitment.addChild(new Node("David", Type.EMPLOYEE));
        payroll.addChild(new Node("Emma", Type.EMPLOYEE));

        CompanyHierarchy hierarchy =
                new CompanyHierarchy(company);

        System.out.println(hierarchy.findClosestDepartment(
                Arrays.asList("Alice", "Bob")).name);
        // Backend

        System.out.println(hierarchy.findClosestDepartment(
                Arrays.asList("Alice", "Charlie")).name);
        // Engineering

        System.out.println(hierarchy.findClosestDepartment(
                Arrays.asList("Alice", "David")).name);
        // Company

        System.out.println(hierarchy.findClosestDepartment(
                Arrays.asList("David", "Emma")).name);
        // HR

        System.out.println(hierarchy.findClosestDepartment(
                Arrays.asList("Alice", "Unknown")));
        // null
    }
}

