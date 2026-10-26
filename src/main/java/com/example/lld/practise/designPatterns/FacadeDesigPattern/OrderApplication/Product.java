package com.example.lld.practise.designPatterns.FacadeDesigPattern.OrderApplication;

import lombok.Data;

@Data
public class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
}
