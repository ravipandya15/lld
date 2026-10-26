package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class Tree extends LivingThings {

    BreatheImplementor breatheImplementor;

    public Tree(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
    }
    @Override
    public void breatheProcess() {
        this.breatheImplementor.breathe();
    }
}
