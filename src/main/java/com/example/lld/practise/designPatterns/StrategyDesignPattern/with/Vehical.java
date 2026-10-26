package com.example.lld.practise.designPatterns.StrategyDesignPattern.with;

import com.example.lld.practise.designPatterns.StrategyDesignPattern.with.strategy.DriveStrategy;

public class Vehical {

    DriveStrategy driveStrategy;

    public Vehical(DriveStrategy strategy) {
        this.driveStrategy = strategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}
