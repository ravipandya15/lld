package com.example.lld.practise.designPatterns.StrategyDesignPattern.with;

import com.example.lld.practise.designPatterns.StrategyDesignPattern.with.strategy.NormalDriveStrategy;

public class PassengerVehical extends Vehical {

    public PassengerVehical() {
        super(new NormalDriveStrategy());
    }
}
