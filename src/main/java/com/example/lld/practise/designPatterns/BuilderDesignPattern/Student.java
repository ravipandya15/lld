package com.example.lld.practise.designPatterns.BuilderDesignPattern;

import java.util.List;

public class Student {

    int rollNumber;
    int age;
    String name;
    String fatherName;
    String motherName;
    List<String> subjects;

    public Student(StudentBuilder studentBuilder) {
        this.rollNumber = studentBuilder.rollNumber;
        this.age = studentBuilder.age;
        this.name = studentBuilder.name;
        this.fatherName = studentBuilder.fatherName;
        this.motherName = studentBuilder.motherName;
        this.subjects = studentBuilder.subjects;
    }

    public String toString() {
        return "" + " rollNo: " + this.rollNumber
                 + " age: " + this.age
                + " name: " + this.name
                + " fatherName: " + this.fatherName
                + " motherName: " + this.motherName
                + " subjects: " + this.subjects.get(0) + ", " + this.subjects.get(1) + ", " + this.subjects.get(2);
    }
}
