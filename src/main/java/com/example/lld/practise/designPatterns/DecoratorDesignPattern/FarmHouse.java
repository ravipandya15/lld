package com.example.lld.practise.designPatterns.DecoratorDesignPattern;

public class FarmHouse extends Pizza {

    int cost = 200;

    @Override
    public int cost() {
        return cost;
    }
}
