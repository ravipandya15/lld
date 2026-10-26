package com.example.lld.practise.Coding_Questions.StreamAPI;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
class Student {
    String name;
    List<String> skills;

    public Student(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;
    }
}

public class MapAndFlatMap {

    // Best example to understand difference between Map and FlatMap

//    List to stream -> list.stream()
//    Array to stream -> Arrays.stream()
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ravi", List.of("Java", "Spring")),
                new Student("Raj", List.of("Python", "Django")),
                new Student("Mohit", List.of("NodeJS", "React"))
        );

        List<List<String>> skills = students.stream()
                .map(Student::getSkills)
                .collect(Collectors.toList());

        System.out.println(skills);

        List<String> flatSkills = students.stream()
                .flatMap(student -> student.getSkills().stream())
                .collect(Collectors.toList());

        System.out.println(flatSkills);

        //    Array to stream -> Arrays.stream()

        String allSkills = students.stream()
                .flatMap(student -> student.getSkills().stream())
                        .collect(Collectors.joining(", "));

        System.out.println(allSkills);
    }
}
