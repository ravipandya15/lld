package com.example.lld.practise.designPatterns.DecoratorDesignPattern.ShoppingCartExample;

public class Main {

    public static void main(String[] args) {

        Product item1 = new Item1("FAN", 1000, ProductType.ELECTRONICS_GOODS);
        Product item2 = new Item2("SOFA", 2000, ProductType.FURNITURE_GOODS);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(item1);
        shoppingCart.addProduct(item2);

        System.out.println("total price with eligible discount is: " + shoppingCart.getTotalPrice());
    }
}
