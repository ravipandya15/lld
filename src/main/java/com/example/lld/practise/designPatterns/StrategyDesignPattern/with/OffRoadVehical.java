package com.example.lld.practise.designPatterns.StrategyDesignPattern.with;

import com.example.lld.practise.designPatterns.StrategyDesignPattern.with.strategy.SpecialDriveStrategy;

public class OffRoadVehical extends Vehical {

    public OffRoadVehical() {
        super(new SpecialDriveStrategy());
    }
}
