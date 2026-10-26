package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode;

import com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SplitwiseMain {
    public static void main(String[] args) {
        User ravi = new User(1, "Ravi");
        User raj = new User(2, "Raj");
        User mohit = new User(3, "Mohit");

        Group group1 = new Group("Bangalore Group", List.of(ravi, raj, mohit));

        SplitWise splitWise = new SplitWise();
        group1.getUsers().stream().forEach(x -> splitWise.AddUser(x));

        Expense expense1 = new Expense(1, "Uber cab", ravi, group1, new Date(), new EqualSplit(1, SplitType.EQUAL, 900.0));
        Expense expense2 = new Expense(1, "Sunday Lunch", raj, group1, new Date(), new EqualSplit(2, SplitType.EQUAL, 1500.0));
        Expense expense3 = new Expense(3, "Sunday Lunch", mohit, group1, new Date(),
                new ExactSplit(3, SplitType.EXACT, 1200.0, Map.of(ravi, 200.0, raj, 600.0, mohit, 400.0)));
        Expense expense4 = new Expense(4, "Party", ravi, group1, new Date(),
                new PercentageSplit(4, SplitType.PERCENTAGE, 1000.0, Map.of(ravi, 30.0, raj, 60.0, mohit, 10.0)));

        splitWise.addExpense(expense1);
        splitWise.addExpense(expense2);
        splitWise.addExpense(expense3);
        splitWise.addExpense(expense4);

        for (User user: splitWise.getUserList().values()) {
            System.out.println("=====  " + user.getUserName() + " ============");
            System.out.println("Total Owes Amount: " + user.getOwesAmount());
            for (Map.Entry<User, Double> entry:  user.getOwes().entrySet()) {
                System.out.println(user.getUserName() + " owes " + entry.getKey().getUserName() + " :amount " + entry.getValue());
            }
            System.out.println("Total Owns Amount: " + user.getOwnsAmount());
            for (Map.Entry<User, Double> entry:  user.getOwns().entrySet()) {
                System.out.println(user.getUserName() + " owns " + entry.getKey().getUserName() + " :amount " + entry.getValue());
            }
            System.out.println("Final Amount: " + user.getFinalAmount());
        }
    }

}
