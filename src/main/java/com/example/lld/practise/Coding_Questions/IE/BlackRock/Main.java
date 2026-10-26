package com.example.lld.practise.Coding_Questions.IE.BlackRock;

public class Main {

    // 1. Coding question
    // 2. Stream API Group by
    // 3. Mediator Design pattern -> how you used in existing project
    // Questions
    /*
    you have an array of integer given and oddCount given. Count number of  sunArrays (continous) which has exactly oddCount oddNumbers.

    [2,5,4,9], oddCount = 1
    Ans:
    [2,5] -
    [5] -
    [2,5,4] -
    [5,4] -
    [4,9] -
    [9] -

    [2]
    [4]


    2
    5
    2,5
    4
    5,4
    2,5,4
    4,9
    9

    Approach1: brute force: TC -> O(N2)
    Approach2: TC -> O(N), SC -> O(N)
        With HashMap, will store count of oddNumbers for each index
    like 1, 2 -> means till not oddCount 1 comes 2 times.
    then currOddCount = 2, so will check map.ContainsKey(2 - 1) exist? if  yes then add count to ans.
    Same as count subArray with 0 sum.
    Approach3: TC-> O(N), SC -> O(1)
    count subArrays with AtMost k times - countsubArrays with Atmost  (k-1) times = subArrays with exactly k times

    2
    5
    2,5
    4
    5,4
    2,5,4
    4,9
    9


    Minus

    2
    4

    Equals


    5
    2,5
    5,4
    2,5,4
    4,9
    9

    which is 6. best optimal solution.


    2. Stream API Question

        Student {
            int id;
            String name;
            String department;
        }


        List<Student> students = new ArrayList<>();

        History -> {Student1, Student2, Student3}
        Science -> {Student4, Student5}

        Map<String, List<Student>> groupStudentsByDepartment = students.stream().collect(Collectors.groupingBy(Student::department));

        3. Mediator Design pattern example



            EmployeeController {
                Mediator<T, R> mediator;
                //EmployeeService employeeService;

                public ResponseBody getEmployee(int empId) {
                    employeeService.getEmployee(empId);
                }
            }

            ProductController {
                Mediator<T, R> mediator;
                //ProductService productService;

                public ResponseBody getProduct(int productId) {
                    productService.getProduct(productId);
                }
            }

            class Mediator<T, R> : MediatorStruct {

                EmployeeService employeeService;
                ProductService productService;
                NotificationService notificationService;

                T request;
                R response;

                Mediator(T request) {
                    this.request = request;
                }

                public R getResponse() {
                    try {
                        if (request instaceOf Employee) {
                            response = employeeService.getEmployee(request);
                        } else if (request instaceOf Product) {
                            response = prouctService.getProduct(request);
                        }
                    } catch (Exception ex) {

                    }
                    return response;
                }

            }
    */
}
