package com.example.lld.practise.designPatterns.StrategyDesignPattern.with.strategy;

public class NormalDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Normal Drive capability");
    }
}
