package com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Solution;

import com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Problem.Sprites;

import java.util.HashMap;
import java.util.Map;

public class RobotFactory {

    private static final Map<String, IRobot> robotMapCache = new HashMap<>();

    public static IRobot createRobot(String type) {
        if (robotMapCache.containsKey(type)) {
            return robotMapCache.get(type);
        }

        if (type.equals("HUMANOID")) {
            Sprites humanoidSprite = new Sprites();
            IRobot robot = new HumanoidRobot(type, humanoidSprite);
            robotMapCache.put(type, robot);
        } else if (type.equals("ROBOTICDOG")) {
            Sprites robotDogSprite = new Sprites();
            IRobot robot = new RoboticDogRobot(type, robotDogSprite);
            robotMapCache.put(type, robot);
        }
        return null;


    }
}
