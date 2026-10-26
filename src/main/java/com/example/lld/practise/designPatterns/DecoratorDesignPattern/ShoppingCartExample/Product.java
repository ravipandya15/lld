package com.example.lld.practise.designPatterns.DecoratorDesignPattern.ShoppingCartExample;

public abstract class Product {
    String productName;
    double productPrice;
    ProductType productType;

    public Product() {}

    public Product(String productName, double productPrice, ProductType productType) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
    }

    public abstract double getPrice();

    public ProductType getProductType() {
        return productType;
    }
}
