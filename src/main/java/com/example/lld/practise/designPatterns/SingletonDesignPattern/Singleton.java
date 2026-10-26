package com.example.lld.practise.designPatterns.SingletonDesignPattern;

public class Singleton {

    // The sole instance of the class
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialization code here
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            // This block is synchronized to make it thread-safe
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // An example method that can be used anywhere
    public void exampleMethod() {
        System.out.println("Singleton method called");
    }
}
