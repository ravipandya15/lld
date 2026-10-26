package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public abstract class LivingThings {

    BreatheImplementor breatheImplementor;

    // difference between abstract class and interface.
//    abstract class can have a constructor, which interface doesn't have constructor
    public LivingThings(BreatheImplementor breatheImplementor) {
        this.breatheImplementor = breatheImplementor;
    }

    public abstract void breatheProcess();
}
