package com.example.lld.practise.designPatterns.AdapterDesignPattern.Adapter;

import com.example.lld.practise.designPatterns.AdapterDesignPattern.Adaptee.WeightMachine;

public class WeightMachineAdapterImpl implements WeightMachineAdapter {

    private final WeightMachine weightMachine;

    public WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }

    @Override
    public double getWeightInKg() {
        double weightInPound = weightMachine.getWeightInPound();

        // Conversion logic from pound to kg
        double weightInKg = weightInPound * 0.45;
        return weightInKg;
    }
}
