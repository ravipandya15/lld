package com.example.lld.practise.designPatterns.FactoryDesignPattern;

public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shapeObj = factory.getShape("Circle");
        shapeObj.draw();
    }
}
