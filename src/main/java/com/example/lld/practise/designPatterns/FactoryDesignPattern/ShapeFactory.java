package com.example.lld.practise.designPatterns.FactoryDesignPattern;

public class ShapeFactory {

    public Shape getShape(String input) {
        switch (input) {
            case "Circle":
                return new Circle(); // as already return statement is present so break is not required.
//                break; // compile time error -> unreachable statement
            case "Rectangle":
                return new Rectangle();
            case "Square":
                return new Square();
            default:
                return null;
        }
    }
}
