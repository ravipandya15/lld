package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class TreeBreatheImplementor implements BreatheImplementor {
    @Override
    public void breathe() {
        System.out.println("Tree breathe implementor");
        // breathe through Leaves
        // Inhale Carbon Dioxide from Air
        // Exhale Oxygen
    }
}
