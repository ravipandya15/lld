package com.example.lld.practise.designPatterns.NullObjectPattern;

public class VehicleFactory {

    static Vehicle getVehicleFactory(String type) {
        if ("Car".equalsIgnoreCase(type)) {
            return new Car();
        }
        return new NullVehicle();
    }
}
