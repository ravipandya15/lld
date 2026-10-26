package com.example.lld.practise.designPatterns.NullObjectPattern;

public class NullVehicle implements Vehicle {

    // default behaviour

    @Override
    public int getTankCapacity() {
        return 0;
    }

    @Override
    public int getSeatingCapacity() {
        return 0;
    }
}
