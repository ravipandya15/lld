package com.example.lld.practise.designPatterns.AdapterDesignPattern.Client;


import com.example.lld.practise.designPatterns.AdapterDesignPattern.Adaptee.WeightMachineForBabies;
import com.example.lld.practise.designPatterns.AdapterDesignPattern.Adapter.WeightMachineAdapter;
import com.example.lld.practise.designPatterns.AdapterDesignPattern.Adapter.WeightMachineAdapterImpl;

public class AdapterClient {
    public static void main(String[] args) {
        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineForBabies());
        double weightInKg = weightMachineAdapter.getWeightInKg();
        System.out.println("Weight in kg is: " + weightInKg);
    }
}
