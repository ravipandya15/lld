package com.example.lld.practise.designPatterns.SingletonDesignPattern;

public class SingleObject {
    private final String name = "Ravi";
    private static SingleObject instance;

    private SingleObject() {}

    public static SingleObject getInstance() {
            synchronized (SingleObject.class) {
                if (instance == null) {
                    instance = new SingleObject();
                }
            }
        return instance;
    }

    public void showMessage() {
        System.out.println("Message from: " + name);
    }
}
