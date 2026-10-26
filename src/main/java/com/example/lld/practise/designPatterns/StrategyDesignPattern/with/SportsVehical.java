package com.example.lld.practise.designPatterns.StrategyDesignPattern.with;

import com.example.lld.practise.designPatterns.StrategyDesignPattern.with.strategy.SpecialDriveStrategy;

public class SportsVehical extends Vehical {
    public SportsVehical() {
        super(new SpecialDriveStrategy());
    }
}
