package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.MediatorDesignPatterns;


import lombok.Data;

@Data
public class Bidder implements Colleague {
    private String name;
    private AuctionMediator auctionMediator;

    public Bidder(String name, AuctionMediator auctionMediator) {
        this.name = name;
        this.auctionMediator = auctionMediator;
        auctionMediator.addBidder(this);
    }

    @Override
    public void placeBid(int bidAmount) {
        this.auctionMediator.placeBid(this, bidAmount);
    }

    @Override
    public void receiveBidNotification(int bidAmount) {
        System.out.println("Bidder: " + this.name + " received notification of bidAmount: " + bidAmount);
    }

    @Override
    public void receiveBidNotification(String msg) {
        System.out.println(msg);
    }
}
