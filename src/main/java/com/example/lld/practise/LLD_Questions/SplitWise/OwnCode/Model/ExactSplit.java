package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExactSplit extends Split {
    Map<User, Double> splitDetails;

    public ExactSplit(int id, SplitType type, double amount, Map<User, Double> splitDetails) {
        super(id, type, amount);
        this.splitDetails = splitDetails;
    }
}
