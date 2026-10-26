package com.example.lld.practise.designPatterns.DecoratorDesignPattern.ShoppingCartExample;

import java.util.ArrayList;
import java.util.List;

public class TypeCouponDecorator extends CouponDecorator {

    Product product;
    int discountPercentage;
    ProductType type;
    static List<ProductType> eligibleProductTypes = new ArrayList<>();

    static {
        eligibleProductTypes.add(ProductType.FURNITURE_GOODS);
        eligibleProductTypes.add(ProductType.DECORATIVE_GOODS);
    }

    public TypeCouponDecorator(Product product, int percentage, ProductType productType) {
        this.product = product;
        this.discountPercentage = percentage;
        this.type = productType;
    }

    @Override
    public double getPrice() {
        double price = product.getPrice();
        if (eligibleProductTypes.contains(this.type)) {
            return price - (price * discountPercentage / 100);
        }
        return price;
    }
}
