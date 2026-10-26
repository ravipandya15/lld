package com.example.lld.practise.LLD_Questions.SplitWise.model.expense;

import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.ExactSplit;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.Split;

import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        double totalAmount = 0;
        double totalSplitAmount = 0;
        for (Split split: getSplits()) {
            if (!(split instanceof ExactSplit)) return false;
            ExactSplit exactSplit = (ExactSplit) split;
            totalSplitAmount += exactSplit.getAmount();
        }
        return totalAmount == totalSplitAmount; // something need to check, as totalAmount is initialized to 0.
    }
}
