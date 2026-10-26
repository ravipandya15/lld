package com.example.lld.practise.Coding_Questions.immutability;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        ImmutableClass p1 = new ImmutableClass(1, 2);
        System.out.println(p1); // Output: Point{x=1, y=2}

        // The following line would cause a compile error, as there are no setters
        // p1.setX(3); // Error: cannot find symbol


        // **************************************************** //

        List<String> subjects = new ArrayList<>();
        subjects.add("Math");
        subjects.add("Science");

        User user1 = new User("u1", "Ravi");

        ImmutableClassWithMutableFields student = new ImmutableClassWithMutableFields("Alice", subjects, user1);
        System.out.println(student); // Output: Student{name='Alice', subjects=[Math, Science]}

        // Trying to modify the original subjects list
        subjects.add("History");
        System.out.println(student); // Output: Student{name='Alice', subjects=[Math, Science]}

        user1 = new User("u2", "Raj");

        System.out.println("student after user1 gets updated");

        System.out.println(student);

        // Accessing subjects through the getter
        List<String> studentSubjects = student.getSubjects();
        System.out.println(studentSubjects); // Output: [Math, Science]

        // Attempting to modify the returned list
        studentSubjects.add("English"); // This will throw UnsupportedOperationException

        System.out.println(studentSubjects); // Output: [Math, Science]


    }
}
