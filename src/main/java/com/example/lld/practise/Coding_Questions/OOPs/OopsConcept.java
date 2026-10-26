package com.example.lld.practise.Coding_Questions.OOPs;

public class OopsConcept {

    public static void main(String[] args) {
        String test1 = test1();
        System.out.println(test1);
    }

    public static String test1() {
        try {
            int a = 10 / 0;
        } catch(Exception ex) {
            return "exception";
        } finally {
            return "finally";
        }
    }

    public void define() {
        A a = new B();
        a.show();
//        a.config(); // gives me compile time error
    }
}
