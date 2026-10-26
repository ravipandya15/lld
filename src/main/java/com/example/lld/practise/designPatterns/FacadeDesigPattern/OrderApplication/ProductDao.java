package com.example.lld.practise.designPatterns.FacadeDesigPattern.OrderApplication;

public class ProductDao {

    public Product getProduct(String productId) {
        return new Product();
    }
}
