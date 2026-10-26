package com.example.lld.practise.Coding_Questions.OOPs.Interface_With_Abstract_Class;

public abstract class AbstractEmployee implements IEmployee {

    // instead of override methods in class, we can also create it as abstract class so we won't get compile time error.

    public abstract void printEmployee(); // we can still create abstract method with same signature, just need to add abstract access modifier.
}
