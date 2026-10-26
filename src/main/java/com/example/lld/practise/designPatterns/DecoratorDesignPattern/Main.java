package com.example.lld.practise.designPatterns.DecoratorDesignPattern;

import com.example.lld.practise.designPatterns.DecoratorDesignPattern.decorator.ExtraCheese;
import com.example.lld.practise.designPatterns.DecoratorDesignPattern.decorator.Mushroom;

public class Main {
    public static void main(String[] args) {

        Pizza farmHouse = new FarmHouse();
        System.out.println("farmhouse pizza cost: " + farmHouse.cost());

        Pizza farmHouseWithExtraCheese = new ExtraCheese(farmHouse);
        System.out.println("farmhouse pizza with extra cheese cost: " + farmHouseWithExtraCheese.cost());

        Pizza farmHouseWithMushroom = new Mushroom(farmHouse);
        System.out.println("farmhouse pizza with mushroom cost: " + farmHouseWithMushroom.cost());

        Pizza farmHouseWithExtraCheeseAndMushroom = new Mushroom(farmHouseWithExtraCheese);
        System.out.println("farmhouse pizza with extra cheese and mushroom cost: " + farmHouseWithExtraCheeseAndMushroom.cost());

    }
}
