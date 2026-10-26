package com.example.lld.practise.designPatterns.DecoratorDesignPattern.ShoppingCartExample;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        Product productWithEligibleDiscount = new TypeCouponDecorator(new PercentageCouponDecorator(product, 10), 3, product.getProductType());
        productList.add(productWithEligibleDiscount);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product p: productList) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }
}
