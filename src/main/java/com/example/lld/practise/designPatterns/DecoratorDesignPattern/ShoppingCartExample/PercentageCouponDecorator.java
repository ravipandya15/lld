package com.example.lld.practise.designPatterns.DecoratorDesignPattern.ShoppingCartExample;

public class PercentageCouponDecorator extends CouponDecorator {

    Product product;
    int discountPercentage;

    public PercentageCouponDecorator(Product product, int percentage) {
        this.product = product;
        this.discountPercentage = percentage;
    }

    @Override
    public double getPrice() {
        double price = product.getPrice();
        return price - (price * discountPercentage / 100);
    }
}
