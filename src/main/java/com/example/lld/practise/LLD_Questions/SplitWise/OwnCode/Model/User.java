package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class User {
    int userId;
    String userName;
    Map<User, Double> owes;
    Map<User, Double> owns;
    double owesAmount;
    double ownsAmount;
    double finalAmount;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        owns = new HashMap<>();
        owes = new HashMap<>();
    }

//    public Double getTotalOwesAmount() {
//        return owes.entrySet().stream()
//                .mapToDouble(x ->  x.getValue())
//                .sum();
//    }
//
//    public Double getTotalOwnsAmount() {
//        return owns.entrySet().stream()
//                .mapToDouble(x ->  x.getValue())
//                .sum();
//    }

    public void updateOwesAmount(double amount) {
        owesAmount += amount;
        finalAmount += amount;
    }

    public void updateOwnsAmount(double amount) {
        ownsAmount -= amount;
        finalAmount -= amount;
    }
}
