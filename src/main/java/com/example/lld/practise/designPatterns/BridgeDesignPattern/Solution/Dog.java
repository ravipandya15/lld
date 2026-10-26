package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class Dog extends LivingThings {
    
    BreatheImplementor breatheImplementor;

    public Dog(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
    }
    @Override
    public void breatheProcess() {
        this.breatheImplementor.breathe();
    }
}
