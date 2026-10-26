package com.example.lld.practise.designPatterns.BridgeDesignPattern.Solution;

public class Client {
    public static void main(String[] args) {
        LivingThings fish = new Fish(new WaterBreatheImplementor());
        fish.breatheProcess();

        LivingThings tree = new Tree(new TreeBreatheImplementor());
        tree.breatheProcess();

        LivingThings dog = new Dog(new LandBreatheImplementor());
        dog.breatheProcess();
    }
}
