package com.example.lld.practise.Coding_Questions.immutability;

import java.util.List;

public final class ImmutableClassWithMutableFields {
    private final String name;
    private final List<String> subjects; // Mutable field
    private final User user;

    // Constructor
    public ImmutableClassWithMutableFields(String name, List<String> subjects, User user) {
        this.name = name;
        // Create a defensive copy of the list to ensure immutability
        this.subjects = List.copyOf(subjects); // Use List.copyOf() in Java 10+
//        Map.copyOf() is also present
        this.user = new User(user);
    }

    public User getUser() {
        return new User(user);
    }

    // Getters
    public String getName() {
        return name;
    }

    // Return a copy of the subjects to ensure the original cannot be modified
    public List<String> getSubjects() {
        return List.copyOf(subjects); // Return a copy
    }

    // Override equals(), hashCode(), and toString() for better usability
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImmutableClassWithMutableFields)) return false;
        ImmutableClassWithMutableFields student = (ImmutableClassWithMutableFields) obj;
        return name.equals(student.name) && subjects.equals(student.subjects);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + subjects.hashCode();
    }

    @Override
    public String toString() {
        return "ImmutableClassWithMutableFields{" +
                "name='" + name + '\'' +
                ", subjects=" + subjects +
                ", user=" + user +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Student{" + "name='" + name + '\'' + ", subjects=" + subjects + '}';
//    }
}
