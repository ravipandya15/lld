package com.example.lld.practise.LLD_Questions.SplitWise.service;

import com.example.lld.practise.LLD_Questions.SplitWise.model.ExpenseType;
import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import com.example.lld.practise.LLD_Questions.SplitWise.model.expense.*;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.PercentageSplit;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.Split;

import java.util.List;

public class ExpenseService {

    public static Expense createExpense(ExpenseType expenseType, double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {

        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, expensePaidBy, splits, expenseData);
            case PERCENTAGE:
                for (Split split: splits) {
                    PercentageSplit percentageSplit = (PercentageSplit) split;
                    split.setAmount((amount * percentageSplit.getPercent()) / 100);
                }
                return new PercentageExpense(amount, expensePaidBy, splits, expenseData);
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
                for (Split split: splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(amount, expensePaidBy, splits, expenseData);
            default:
                return null;
        }
    }
}
