package com.example.lld.practise.designPatterns.SingletonDesignPattern;

public class Main {
    public static void main(String[] args) {
        // Access the singleton instance
        Singleton singleton = Singleton.getInstance();

        SingleObject singleObject = SingleObject.getInstance();
        
        // Call the example method
        singleton.exampleMethod();
        singleObject.showMessage();
    }
}
