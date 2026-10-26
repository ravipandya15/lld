package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class Fish extends LivingThings {

    BreatheImplementor breatheImplementor;

    public Fish(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
        this.breatheImplementor = breatheImplementor;
//        super(breatheImplementor);
    }
    @Override
    public void breatheProcess() {
        this.breatheImplementor.breathe();
    }
}
