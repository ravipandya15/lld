package com.example.lld.practise.LLD_Questions.SplitWise.service;

import com.example.lld.practise.LLD_Questions.SplitWise.model.ExpenseType;
import com.example.lld.practise.LLD_Questions.SplitWise.model.expense.ExpenseData;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.Split;
import com.example.lld.practise.LLD_Questions.SplitWise.repository.ExpenseRepository;

import java.util.List;

public class SplitWiseService {
    ExpenseRepository expenseRepository;

    public SplitWiseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseData expenseData) {
        expenseRepository.addExpense(expenseType, amount, paidBy, splits, expenseData);
    }

    public void showBalanceByUserName(String userName) {
        List<String> balances = expenseRepository.getBalanceByUserName(userName);
        if (balances.isEmpty()) {
            System.out.println("No Balance");
        } else {
            for (String balance: balances) {
                System.out.println(balance);
            }
        }
    }

    public void showBalances() {
        List<String> balances = expenseRepository.getBalances();
        if (balances.isEmpty()) {
            System.out.println("No Balance");
        } else {
            for (String balance: balances) {
                System.out.println(balance);
            }
        }
    }
}
