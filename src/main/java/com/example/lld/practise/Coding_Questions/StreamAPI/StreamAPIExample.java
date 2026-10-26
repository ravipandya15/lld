package com.example.lld.practise.Coding_Questions.StreamAPI;

import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamAPIExample {

    public static void main(String[] args) {
        // creating immutable list

        // in JDK8
        List<String> mutableList = Arrays.asList("a", "b", "c");
        List<String> immutableList = Collections.unmodifiableList(mutableList);

        // in JDK9
        List<String> readOnlyList = List.of("a", "b", "c");
//        readOnlyList.add("v"); // will give unsupported exception
        printCountryWithCityWithHighestPopulation();
        printAllWordsStartsWithHash();
        streamAPIGeneralExamples();

        System.out.println("completed");

        // Find first non empty character from String using java stream.
        findFirstNonRepeatingCharacter();

        findMaxMElementsAfterSkippingNElements(2, 3);
        findMaxMElementsAfterSkippingNElements(3, 3);
    }

    public static void printCountryWithCityWithHighestPopulation() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("India", List.of(new City("Mumbai", 1000), new City("Delhi", 500), new City("Ahmedabad", 200))));
        countries.add(new Country("US", List.of(new City("Saint Luise", 5000), new City("New York", 10000), new City("Kansas City", 6000))));
        countries.add(new Country("UK", List.of(new City("Abc", 1500), new City("London", 5000))));
        countries.add(new Country("Unknown"));

        List<String[]> countryAndCityWithHighestPopulation = countries.stream().map(x -> new String[] { x.getName(),  x.getCitiesList().stream().max(Comparator.comparingInt(city -> city.getPopulation())).orElse(new City()).getName() }).collect(Collectors.toList());
        for (String[] str : countryAndCityWithHighestPopulation) {
            System.out.println(str[0] + ": " + str[1]);
        }

        countries.stream()
                .filter(country -> country.getCitiesList() != null
                        && !country.getCitiesList().isEmpty())
                .forEach(country -> {
                    country.getCitiesList().stream()
                            .max(Comparator.comparingInt(City::getPopulation))
                            .ifPresent(city -> System.out.println(
                                    "Country: " + country.getName()
                                            + ", City: " + city.getName()
                                            + ", Population: " + city.getPopulation()
                            ));
                });

        System.out.println("City with highest population");
        // Solution for in general population
        countries.stream()
                .filter(country -> country.getCitiesList() != null)
                .flatMap(country -> country.getCitiesList().stream()
                        .map(city -> Map.entry(country.getName(), city)))
                .max(Comparator.comparingInt(entry -> entry.getValue().getPopulation()))
                .ifPresent(entry -> System.out.println(
                        "Country: " + entry.getKey()
                                + ", City: " + entry.getValue().getName()
                                + ", Population: " + entry.getValue().getPopulation()
                ));
    }

    public static void printAllWordsStartsWithHash() {

        String[] sentences = {
                "Here is a #sample sentence.",
                "This is another #example of a sentence.",
                "#Java streams are powerful."
        };

//        Set<String> set = Arrays.stream(sentences)
//                .map(s -> s.split(" "))
//                .flatMap(s -> Arrays.stream(s)
//                        .filter(str -> str.startsWith("#")))
//                .collect(Collectors.toSet());

//        Flatmap is for Stream<Stream<String>>

        Arrays.stream(sentences)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> s.startsWith("#"))
                .collect(Collectors.toSet())
                .forEach(str -> System.out.print(str + " "));

        String str = Arrays.stream(sentences)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> s.startsWith("#"))
                .collect(Collectors.joining(", "));

        System.out.println(str);

//        Set<String> set = Arrays.stream(sentences)
//                .flatMap(sentence -> Arrays.stream(sentence.split(" "))
//                        .filter(str -> str.startsWith("#")))
//                .collect(Collectors.toSet());

//        for (String str : set) {
//            System.out.print(str + " ");
//        }
        System.out.println();
    }

    private static void streamAPIGeneralExamples() {
        Employee e1 = new Employee(1, "Ravi", 5000.0, new City("Surat", 1000));
        Employee e2 = new Employee(2, "Raj", 4000.0, new City("Surat", 1000));
        Employee e3 = new Employee(3, "Bhautik", 3000.0, new City("Surat", 1000));
        Employee e4 = new Employee(4, "DJ", 2000.0, new City("Vadodara", 1000));
        Employee e5 = new Employee(5, "Keval", 6000.0, new City("Vadodara", 1000));

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);

        Map<City, List<Employee>> groupBy = employeeList.stream().collect(Collectors.groupingBy(Employee::getCiy));
        for (Map.Entry<City, List<Employee>> map: groupBy.entrySet()) {
            System.out.println("City: " + map.getKey());
            for (Employee e: map.getValue()) {
                System.out.print(e+ " ");
            }
            System.out.println();
        }
    }


    // Java11 features
    public void java11Features() {

//        1. few new methods to the String class: isBlank, lines, strip, stripLeading, stripTrailing, and repeat.
        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
//        assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");

//        2. New file methods
//        Path filePath = Files.writeString(Files.createTempFile(tempDir, "demo", ".txt"), "Sample text");
//        String fileContent = Files.readString(filePath);
//        assertThat(fileContent).isEqualTo("Sample text");

//        3. new default toArray method which takes an IntFunction argument.
        List sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray = (String[]) sampleList.toArray(String[]::new);
        String[] sampleArray1 = (String[]) sampleList.toArray(new String[0]);
//        assertThat(sampleArray).containsExactly("Java", "Kotlin");

//        4. A static not method has been added to the Predicate interface. We can use it to negate an existing predicate, much like the negate method:

        List<String> sampleList1 = Arrays.asList("Java", "\n \n", "Kotlin", " ");
        List withoutBlanks = sampleList1.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
//        assertThat(withoutBlanks).containsExactly("Java", "Kotlin");

//        5. Support for using the local variable syntax (var keyword) in lambda parameters was added in Java 11.
//        We can make use of this feature to apply modifiers to our local variables, like defining a type annotation:

        List<String> sampleList2 = Arrays.asList("Java", "Kotlin");
        String resultString = sampleList2.stream()
                .map((@NonNull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
//        assertThat(resultString).isEqualTo("JAVA, KOTLIN");

//        6. 3.8. Running Java Files
//        A major change in this version is that we don’t need to compile the Java source files with javac explicitly anymore:

//        $ javac HelloWorld.java
//        $ java HelloWorld
//        Hello Java 8!
//
//        Instead, we can directly run the file using the java command:
//
//        $ java HelloWorld.java
//        Hello Java 11!

//        6. A No-Op Garbage Collector called Epsilon
    }

    public void java8Features() {
        /*
        Lambda Expressions: Concise functional code using ->.
        Functional Interfaces: Single-method interfaces.
        Introduced and Improved APIs:
            Stream API: Efficient Data Manipulation.
            Date/Time API: Robust Date and Time Handling.
            Collection API Improvements: Enhanced Methods for Collections (e.g., removeIf, replaceAll).
            Concurrency API Improvements: New classes for parallel processing (e.g., CompletableFuture).
        Optional Class: Handle null values safely.
        forEach() Method in Iterable Interface: Executes an action for each element in a Collection.
        Default Methods: Evolve interfaces without breaking compatibility.
        Static Methods: Allows adding methods with default implementations to interfaces.
        Method References: Refer to methods easily.
        default and static methods
        forEach() method
        Java Stream API


        Q3: What is a functional interface?

        A functional interface is an interface that contains just one abstract method.


                Q4: How are functional interfaces and Lambda Expressions related?

        Lambda expressions are applied only to the functional interface’s abstract method.

         */


    }

    private static void EPAMQuestions() {

//        s1 = "Hello world Hello world Hello world"
//        s2 = "ello w"
//
//        count how many times s2 comes in s1 using stream API

        Integer[] arr = new Integer [] {2, 10, 7, 15, 4, 9, 11};

        int sum = Arrays.stream(arr).distinct().reduce(0, Integer::sum);

        List<Integer> list = Arrays.stream(arr).sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());

        List<Integer> set = Arrays.stream(arr).filter(x -> x % 5 == 0).collect(Collectors.toList());
    }

    static void findFirstNonRepeatingCharacter() {
        String str = "abbcda";

        Map<String, Long> map = Arrays.asList(str.split(""))
                .stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        map.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .ifPresentOrElse(
                        e -> System.out.println(e.getKey()),
                        () -> System.out.println("There is no repeating character"));

        // Not working
//        Map<char[], Long> map = Arrays.asList(str.toCharArray())
//                .stream()
//                .collect(Collectors.groupingBy(
//                        Function.identity(),
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ));
//
//        map.entrySet().stream()
//                .filter(e -> e.getValue() == 1)
//                .findFirst()
//                .ifPresentOrElse(
//                        e -> System.out.println(e.getKey()),
//                        () -> System.out.println("There is no repeating character"));
    }

    // find max 3 elements using stream api

    private static void findMaxMElementsAfterSkippingNElements(int N, int M) {
        List<Integer> arr = Arrays.asList(9000, 3000, 5000,8000, 6000);

         List<Integer> res = arr.stream().sorted().toList().reversed().stream().skip(N).limit(M).toList();

        List<Integer> res1 = arr.stream().sorted(Comparator.reverseOrder()).skip(N).limit(M).toList();

        System.out.println(res);
        System.out.println(res1);
    }
    // filter x % 5 == 0 and x / 2 == 0 and sum it.
    // find unique elements from list and sum it.
}
