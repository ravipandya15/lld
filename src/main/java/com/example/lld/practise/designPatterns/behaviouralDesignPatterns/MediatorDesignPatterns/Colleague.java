package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.MediatorDesignPatterns;

public interface Colleague {
    void placeBid(int bidAmount);
    void receiveBidNotification(String msg);
    void receiveBidNotification(int bidAmount);
    String getName();
}
