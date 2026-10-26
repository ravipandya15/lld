package com.example.lld.practise.LLD_Questions.SplitWise.model.expense;

import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.PercentageSplit;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.Split;

import java.util.List;

public class PercentageExpense extends Expense {

    public PercentageExpense(double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        double totalSplitPercentage = 0;
        for (Split split: getSplits()) {
            if (!(split instanceof PercentageSplit)) return false;
            PercentageSplit percentageSplit = (PercentageSplit) split;
            totalSplitPercentage += percentageSplit.getPercent();
        }
        return totalSplitPercentage == 100;
    }
}
