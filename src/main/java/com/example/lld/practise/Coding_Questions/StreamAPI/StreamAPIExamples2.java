package com.example.lld.practise.Coding_Questions.StreamAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
class Employees {
    String name;
    int age;
}

@Getter
@Setter
@AllArgsConstructor
class StudentScore {
    String name;
    int score;
}

public class StreamAPIExamples2 {
    static void main() {

        List<Employees> employees = List.of(
                new Employees("A", 22),
                new Employees("B", 25),
                new Employees("C", 32),
                new Employees("D", 35),
                new Employees("E", 42),
                new Employees("F", 28)
        );

        groupEmployeesByRange(employees, 10);
        deleteDuplicateAndKeepOneForEach();
        getHighestAverageRoundedDown();
    }

    // Group employees into age ranges like 20-30 and 30-40 using Java streams, and keep it generic.
    private static void groupEmployeesByRange(List<Employees> employees, int rangeSize) {
        Map<String, List<Employees>> groupedEmployees =
                employees.stream()
                        .collect(Collectors.groupingBy(employee -> {
                            int start = (employee.getAge() / rangeSize) * rangeSize;
                            int end = start + rangeSize;
                            return start + "-" + end;
                        }, LinkedHashMap::new, Collectors.toList()));

        groupedEmployees.forEach((range, list) -> {
            System.out.print(range + " ->");

            list.forEach(e ->
                    System.out.print(e.getName() + "(" + e.getAge() + "), ")
            );

            System.out.println();
        });


        Map<String, String> groupedEmployeesWithComma =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                employee -> {
                                    int start = (employee.getAge() / rangeSize) * rangeSize;
                                    int end = start + rangeSize;
                                    return start + "-" + end;
                                },
                                LinkedHashMap::new,
                                Collectors.toList()
                        ))
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().stream()
                                        .map(Employees::getName)
                                        .collect(Collectors.joining(",")),
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));


        System.out.println("separate with comma");
        for (Map.Entry<String, String> entry: groupedEmployeesWithComma.entrySet()) {
            System.out.println(entry.getKey() + "- " + entry.getValue());
        }
    }

    //    A table has duplicate rows. Delete the duplicates and keep one of each.

    /*
        DELETE FROM employees
        WHERE id NOT IN (
            SELECT min_id
            FROM (
                    SELECT MIN(id) as min_id
                    FROM employees
                    GROUP BY name, age
            )
        )
            id	name	age
            1	Ravi	25
            2	Ravi	25
            3	Amit	30
            4	Ravi	25

     */
    public static void deleteDuplicateAndKeepOneForEach() {
        List<Employee> employees = List.of(
                new Employee(1, "Ravi", 25),
                new Employee(2, "Ravi", 25),
                new Employee(3, "Amit", 30),
                new Employee(4, "Ravi", 25)
        );

        List<Employee> uniqueEmployees = employees.stream()
                .collect(Collectors.toMap(
                        e -> e.getEmpName() + "-" + e.getAge(),
                        Function.identity(),
                        (first, second) -> first,
                        LinkedHashMap::new
                ))
                .values()
                .stream()
                .toList();

        System.out.println(uniqueEmployees);
    }

//    Given rows of student name and score, return the highest average score, rounded down.
    /*
        student_name	score
        Ravi	        80
        Ravi	        90
        Amit	        70
        Amit	        85
        Neha	        95

        select FLOOR(avg(score)) as highest_average
        from student_score
        group by student_name
        order by avg(score) desc
        limit 1;
     */
    private static void getHighestAverageRoundedDown() {
        List<StudentScore> scores = List.of(
                new StudentScore("Ravi", 80),
                new StudentScore("Ravi", 90),
                new StudentScore("Amit", 70),
                new StudentScore("Amit", 85),
                new StudentScore("Neha", 95)
        );

        int highestAverage = scores.stream().collect(Collectors.groupingBy(
                StudentScore::getName,
                Collectors.averagingInt(StudentScore::getScore)
        )).values()
                .stream()
                .mapToDouble(x -> Math.floor(x))
                .max()
                .stream()
                .mapToInt(x -> (int)x)
                .findFirst()
                .orElse(0);

        System.out.println(highestAverage);
    }
}
