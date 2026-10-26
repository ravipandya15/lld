package com.example.lld.practise.LLD_Questions.SplitWise.model.split;

import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import lombok.Data;

@Data
public class PercentageSplit extends Split {

    double percent;

    public PercentageSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }
}
