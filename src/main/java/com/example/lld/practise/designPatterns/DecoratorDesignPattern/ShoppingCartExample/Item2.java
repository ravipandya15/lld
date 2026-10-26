package com.example.lld.practise.designPatterns.DecoratorDesignPattern.ShoppingCartExample;

public class Item2 extends Product {

    public Item2(String productName, double productPrice, ProductType productType) {
        super(productName, productPrice, productType);
    }

    @Override
    public double getPrice() {
        return productPrice;
    }
}
