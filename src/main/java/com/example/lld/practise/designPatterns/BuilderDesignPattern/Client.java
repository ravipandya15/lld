package com.example.lld.practise.designPatterns.BuilderDesignPattern;

public class Client {
    public static void main(String[] args) {
        Director dir1 = new Director(new EngineeringStudentBuilder());
        Director dir2 = new Director(new MBAStudentBuilder());

        Student engineeringStudent =  dir1.createStudent();
        Student mbaStudent =  dir2.createStudent();

        System.out.println(engineeringStudent.toString());
        System.out.println(mbaStudent.toString());
    }
}
