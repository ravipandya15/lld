package com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Solution;

import com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Problem.Sprites;

public class RoboticDogRobot implements IRobot {

    private final String type;
    private final Sprites body;

    public RoboticDogRobot(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public Sprites getBody() {
        return body;
    }

    @Override
    public void display(int x, int y) {
        // use the roboticDogRobot sprites object.
        // and X and Y coordinate to render the image.
    }
}
