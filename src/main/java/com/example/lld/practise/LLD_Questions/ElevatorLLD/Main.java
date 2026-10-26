package com.example.lld.practise.LLD_Questions.ElevatorLLD;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Elevator elevator = new Elevator();

        // Add pickup and destination requests
        elevator.addRequest(new Request(3, RequestType.PICK_UP));
        elevator.addRequest(new Request(7, RequestType.DESTINATION));
        elevator.addRequest(new Request(5, RequestType.PICK_DOWN));
        elevator.addRequest(new Request(2, RequestType.DESTINATION));

        System.out.println("Elevator simulation started");
        System.out.println("----------------------------");

        int stepCount = 0;
        int maxSteps = 100;

        while (stepCount < maxSteps) {

            System.out.printf(
                    "Before step %d: Floor = %d, Direction = %s%n",
                    stepCount,
                    elevator.getCurrentFloor(),
                    elevator.getDirection()
            );

            elevator.step();

            System.out.printf(
                    "After step %d:  Floor = %d, Direction = %s%n",
                    stepCount,
                    elevator.getCurrentFloor(),
                    elevator.getDirection()
            );

            System.out.println();

            stepCount++;

            // Stop when all requests have been processed.
            if (elevator.getDirection() == Direction.IDLE) {
                System.out.println("Elevator is IDLE. Simulation finished.");
                break;
            }

            Thread.sleep(1000);
        }

        if (stepCount == maxSteps) {
            System.out.println("Maximum steps reached.");
        }
    }
}