package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class LandBreatheImplementor implements BreatheImplementor {
    @Override
    public void breathe() {
        System.out.println("Land breathe implementor");
        // breathe through NOSE
        // Inhale Oxygen from Air
        // Exhale Carbon Dioxide
    }
}
