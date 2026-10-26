package com.example.lld.practise.designPatterns.FacadeDesigPattern.OrderApplication;

public class OrderClient {

    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.createOrder();
    }
}
