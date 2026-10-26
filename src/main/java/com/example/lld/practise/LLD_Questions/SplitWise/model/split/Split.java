package com.example.lld.practise.LLD_Questions.SplitWise.model.split;

import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Split {
    private User user;
    double amount;

    public Split(User user) {
        this.user = user;
    }
}
