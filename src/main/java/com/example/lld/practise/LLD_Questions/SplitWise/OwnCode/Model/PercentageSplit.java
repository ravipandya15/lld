package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PercentageSplit extends Split {
    private Map<User, Double> percentageDetails;

    public PercentageSplit(int id, SplitType type, double amount, Map<User, Double> percentageDetails) {
        super(id, type, amount);
        this.percentageDetails = percentageDetails;
    }
}
