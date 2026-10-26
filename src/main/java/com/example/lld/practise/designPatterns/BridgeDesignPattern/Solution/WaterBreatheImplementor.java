package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class WaterBreatheImplementor implements BreatheImplementor {
    @Override
    public void breathe() {
        System.out.println("Water breathe implementor");
        // breathe through GILLS
        // Inhale Oxygen from Air
        // Exhale Carbon Dioxide
    }
}
