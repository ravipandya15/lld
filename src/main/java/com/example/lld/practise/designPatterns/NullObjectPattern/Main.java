package com.example.lld.practise.designPatterns.NullObjectPattern;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle  = VehicleFactory.getVehicleFactory("Bike");
        printVehicleDetails(vehicle);
    }

    private static void printVehicleDetails(Vehicle vehicle) {
//        if (vehicle != null) {
            System.out.println("Tank capacity: " + vehicle.getTankCapacity());
            System.out.println("Seating capacity: " + vehicle.getSeatingCapacity());
//        }
    }
}
