package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Split {
    int id;
    SplitType type;
    double amount;

    public Split(int id, SplitType type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }
}
