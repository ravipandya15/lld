package com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Solution;

public class Main {

    public static void main(String[] args) {
        IRobot humanoidRobot1 = RobotFactory.createRobot("HUMANOID");
        humanoidRobot1.display(1, 2);

        IRobot humanoidRobot2 = RobotFactory.createRobot("HUMANOID");
        humanoidRobot2.display(10, 30);

        IRobot roboticDog1 = RobotFactory.createRobot("ROBOTICDOG");
        roboticDog1.display(2, 9);

        IRobot roboticDog2 = RobotFactory.createRobot("ROBOTICDOG");
        roboticDog2.display(11, 37);
    }
}
