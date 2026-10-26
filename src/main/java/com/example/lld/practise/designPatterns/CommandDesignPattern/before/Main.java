package com.example.lld.practise.designPatterns.CommandDesignPattern.before;

public class Main {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.turnOnAC();
        airConditioner.setTempreture(24);
        airConditioner.turnOff();

        // what if I have bulp which use same remote control.
        // lot of stuff from client side. If turning on AC is having bunch of methods to implement.
    }
}
