package com.example.lld.practise.designPatterns.behaviouralDesignPatterns.MediatorDesignPatterns;

import java.util.ArrayList;
import java.util.List;

public class Auction implements AuctionMediator {

    List<Colleague> colleagues = new ArrayList<>();
    int currentBidAmount;

    @Override
    public void addBidder(Colleague colleague) {
        this.colleagues.add(colleague);
    }



    @Override
    public void placeBid(Colleague bidder, int bidAmount) {
        if (bidAmount > currentBidAmount) {
            for (Colleague colleague : colleagues) {
                if (!colleague.getName().equals(bidder.getName())) {
                    colleague.receiveBidNotification(bidAmount);
                }
            }
            this.currentBidAmount = bidAmount;
        } else {
            bidder.receiveBidNotification("Can't place a bid as current bid is higher than your bid.. Place a higher bid");
        }

        System.out.println("===========================================================");
    }
}
