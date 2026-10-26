package com.example.lld.practise.designPatterns.StrategyDesignPattern.with.strategy;

public class SpecialDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Special Drive capability");
    }
}
