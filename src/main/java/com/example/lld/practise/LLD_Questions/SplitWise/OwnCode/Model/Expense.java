package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Expense {
    int id;
    String name;
    User paidBy;
    Group group;
    Date dateTime;
    Split split;

    public Expense(int id, String name, User paidBy, Group group, Date dateTime, Split split) {
        this.id = id;
        this.name = name;
        this.paidBy = paidBy;
        this.group = group;
        this.dateTime = dateTime;
        this.split = split;
    }
}
