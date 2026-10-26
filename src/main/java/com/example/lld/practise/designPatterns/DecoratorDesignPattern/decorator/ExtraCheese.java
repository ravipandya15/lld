package com.example.lld.practise.designPatterns.DecoratorDesignPattern.decorator;

import com.example.lld.practise.designPatterns.DecoratorDesignPattern.Pizza;

public class ExtraCheese extends ToppingDecorator {

    Pizza pizza;

    public ExtraCheese(Pizza pizza) {
        this.pizza = pizza;
    }

    int cheeseCost = 20;
    @Override
    public int cost() {
        return this.pizza.cost() + cheeseCost;
    }
}
