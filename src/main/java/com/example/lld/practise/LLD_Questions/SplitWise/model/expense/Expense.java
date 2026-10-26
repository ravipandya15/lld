package com.example.lld.practise.LLD_Questions.SplitWise.model.expense;

import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.Split;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public abstract class Expense {

    private final String id;
    private double amount;
    private User expensePaidBy;
    private List<Split> splits;
    private ExpenseData expenseData;

    public Expense(double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.expensePaidBy = expensePaidBy;
        this.splits = splits;
        this.expenseData = expenseData;
    }

    public abstract boolean validate();
}
