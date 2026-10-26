package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.MediatorDesignPatterns;

public interface AuctionMediator {
    void addBidder(Colleague colleague);
    void placeBid(Colleague colleague, int bidAmount);
}
