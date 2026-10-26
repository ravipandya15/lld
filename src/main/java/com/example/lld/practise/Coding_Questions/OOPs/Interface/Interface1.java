package com.example.lld.practise.Coding_Questions.OOPs.Interface;

public interface Interface1 {
    public default void show() {
        System.out.println("In Interface1");
    }

    static void test() {
        System.out.println("Static Test Method");
    }
}
