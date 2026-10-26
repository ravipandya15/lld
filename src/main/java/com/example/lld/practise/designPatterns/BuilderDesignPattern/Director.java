package com.example.lld.practise.designPatterns.BuilderDesignPattern;

public class Director {

    StudentBuilder studentBuilder;

    public Director(StudentBuilder studentBuilder) {
        this.studentBuilder = studentBuilder;
    }

    public Student createStudent() {
        if (this.studentBuilder instanceof EngineeringStudentBuilder) {
            return createEngineeringStudent();
        } else if (this.studentBuilder instanceof MBAStudentBuilder) {
            return createMBAStudent();
        }
        return null;
    }

    private Student createEngineeringStudent() {
        return this.studentBuilder.setRollNumber(1).setAge(22).setName("Ravi").setMotherName("Bharatiben").setSubjects().build();
    }

    private Student createMBAStudent() {
        return this.studentBuilder.setRollNumber(2).setAge(25).setName("Parth").setfatherName("Manojkumar").setMotherName("KusumBen").setSubjects().build();
    }
}
