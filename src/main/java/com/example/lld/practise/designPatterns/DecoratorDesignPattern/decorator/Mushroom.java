package com.example.lld.practise.designPatterns.DecoratorDesignPattern.decorator;

import com.example.lld.practise.designPatterns.DecoratorDesignPattern.Pizza;

public class Mushroom extends ToppingDecorator {

    Pizza pizza;

    public Mushroom(Pizza pizza) {
        this.pizza = pizza;
    }

    int mushroomCost = 15;

    @Override
    public int cost() {
        return this.pizza.cost() + mushroomCost;
    }
}
