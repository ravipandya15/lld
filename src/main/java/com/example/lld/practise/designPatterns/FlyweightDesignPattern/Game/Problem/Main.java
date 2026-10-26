package com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Problem;

public class Main {

    public static void main(String[] args) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < 500000; i++) {
            Sprites humanoidSprite = new Sprites();
            Robot humanRobot = new Robot(x + i, y + i, "HUMANOID", humanoidSprite);
        }

        for (int i = 0; i < 500000; i++) {
            Sprites robotDogSprite = new Sprites();
            Robot roboticDog = new Robot(x + i, y + i, "ROBOTICDOG", robotDogSprite);
        }

        // total 10L objects, assume memory occupied by single onject is 31KB
        // total 10L * 31KB = 31GB
    }
}
