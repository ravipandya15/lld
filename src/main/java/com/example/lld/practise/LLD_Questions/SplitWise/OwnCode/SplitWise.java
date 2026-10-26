package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode;

import com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SplitWise {

    Map<String, User> userList;
    List<Expense> expenses;

    public SplitWise() {
        this.userList = new HashMap<>();
        this.expenses = new ArrayList<>();
    }

    public void AddUser(User user) {
        userList.put(user.getUserName(), user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        performSplit(expense);
    }

    public Double getOwnsAmount(User user) {
        return user.getOwnsAmount();
    }

    public Double getOwesAmount(User user) {
        return user.getOwesAmount();
    }

    private void performSplit(Expense expense) {
        SplitType splitType = expense.getSplit().getType();
        User paidBy = expense.getPaidBy();
        if (splitType.equals(SplitType.EQUAL)) {

            Double splitAmount = expense.getSplit().getAmount() / expense.getGroup().getUsers().size();

            for (User user: expense.getGroup().getUsers()) {
                if (user.getUserId() == paidBy.getUserId()) continue;

                user.getOwns().put(paidBy, user.getOwns().getOrDefault(paidBy, 0.0) - splitAmount);
                user.updateOwnsAmount(splitAmount);
                paidBy.getOwes().put(user, paidBy.getOwes().getOrDefault(user, 0.0) + splitAmount);
                paidBy.updateOwesAmount(splitAmount);
            }

        } else if (splitType.equals(SplitType.PERCENTAGE) && expense.getSplit() instanceof PercentageSplit) {
            // validation
            Map<User, Double> details = ((PercentageSplit) expense.getSplit()).getPercentageDetails();
            double totalSplitPercentage = details.entrySet().stream().mapToDouble(x -> x.getValue()).sum();
            if (totalSplitPercentage != 100) {
                System.out.println("Please provide correct percentage");
                return;
            }

            for (Map.Entry<User, Double> splitDetail: details.entrySet()) {
                if (splitDetail.getKey().getUserId() == paidBy.getUserId()) continue;

                Double splitAmount = expense.getSplit().getAmount() * splitDetail.getValue() / 100.0;
                splitDetail.getKey().getOwns().put(paidBy, splitDetail.getKey().getOwns().getOrDefault(paidBy, 0.0) - splitAmount);
                splitDetail.getKey().updateOwnsAmount(splitAmount);
                paidBy.getOwes().put(splitDetail.getKey(), paidBy.getOwes().getOrDefault(splitDetail.getKey(), 0.0) + splitAmount);
                paidBy.updateOwesAmount(splitAmount);
            }


        } else if (splitType.equals(SplitType.EXACT) && expense.getSplit() instanceof ExactSplit) {
            Map<User, Double> details = ((ExactSplit) expense.getSplit()).getSplitDetails();

            // validation
            double totalSplitAmount = details.entrySet().stream().mapToDouble(x -> x.getValue()).sum();
            if (expense.getSplit().getAmount() != totalSplitAmount) {
                System.out.println("Please provide correct split amount");
                return;
            }

            for (Map.Entry<User, Double> splitDetail: details.entrySet()) {
                if (splitDetail.getKey().getUserId() == paidBy.getUserId()) continue;

                Double splitAmount = splitDetail.getValue();
                splitDetail.getKey().getOwns().put(paidBy, splitDetail.getKey().getOwns().getOrDefault(paidBy, 0.0) - splitAmount);
                splitDetail.getKey().updateOwnsAmount(splitAmount);
                paidBy.getOwes().put(splitDetail.getKey(), paidBy.getOwes().getOrDefault(splitDetail.getKey(), 0.0) + splitAmount);
                paidBy.updateOwesAmount(splitAmount);
            }
        }
    }
}