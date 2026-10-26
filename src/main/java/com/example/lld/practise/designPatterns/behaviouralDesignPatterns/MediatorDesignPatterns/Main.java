package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.MediatorDesignPatterns;

public class Main {

    public static void main(String[] args) {
        AuctionMediator auctionMediator = new Auction();
        Colleague c1 = new Bidder("Parth", auctionMediator);
        Colleague c2 = new Bidder("Keval", auctionMediator);
        Colleague c3 = new Bidder("DJ", auctionMediator);

        c1.placeBid(2000);
        c2.placeBid(3000);
        c1.placeBid(10000);
        c3.placeBid(1000);
    }
}
