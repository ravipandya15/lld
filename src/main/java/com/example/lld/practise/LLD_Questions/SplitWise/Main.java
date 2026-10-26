package com.example.lld.practise.LLD_Questions.SplitWise;

import com.example.lld.practise.LLD_Questions.SplitWise.model.CommandType;
import com.example.lld.practise.LLD_Questions.SplitWise.model.ExpenseType;
import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import com.example.lld.practise.LLD_Questions.SplitWise.model.expense.ExpenseData;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.EqualSplit;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.ExactSplit;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.PercentageSplit;
import com.example.lld.practise.LLD_Questions.SplitWise.model.split.Split;
import com.example.lld.practise.LLD_Questions.SplitWise.repository.ExpenseRepository;
import com.example.lld.practise.LLD_Questions.SplitWise.service.SplitWiseService;
import com.example.lld.practise.LLD_Questions.SplitWise.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.lld.practise.LLD_Questions.SplitWise.model.ExpenseType.*;

public class Main {

    public static void main(String[] args) {
        // Input Sample Users

        User user1 = new User(1, "u1", "u1@gmail.com", "9890098900");
        User user2 = new User(2, "u2", "u2@gmail.com", "9999999999");
        User user3 = new User(3, "u3", "u3@gmail.com", "9898989899");
        User user4 = new User(4, "u4", "u4@gmail.com", "8976478292");

        // Adding Expenses
        ExpenseRepository expenseRepository = new ExpenseRepository();
        UserService userService = new UserService(expenseRepository);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        SplitWiseService splitWiseService = new SplitWiseService(expenseRepository);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String[] commands = sc.nextLine().split(" ");

            CommandType commandType = CommandType.of(commands[0]);

            switch (commandType) {
                case EXPENSE:
                    String userName = commands[1];
                    double amountSpend = Integer.parseInt(commands[2]);
                    int totalMembers = Integer.parseInt(commands[3]);
                    List<Split> splits = new ArrayList<>();
                    int expenseIndex = 3 + totalMembers + 1;
                    ExpenseType expenseType = ExpenseType.of(commands[expenseIndex]);

                    switch (expenseType) {
                        case EQUAL:
                            for (int i = 0; i < totalMembers; i++) {
                                splits.add(new EqualSplit(userService.getUser(commands[4 + i])));
                            }
                            splitWiseService.addExpense(EQUAL, amountSpend, userName, splits, new ExpenseData("Goa Flight"));
                            break;
                        case EXACT:
                            for (int i = 0; i < totalMembers; i++) {
                                splits.add(new ExactSplit(userService.getUser(commands[4 + i]), Double.parseDouble(commands[expenseIndex + i + 1])));
                            }
                            splitWiseService.addExpense(EXACT, amountSpend, userName, splits, new ExpenseData("Cab Tickets"));
                            break;
                        case PERCENTAGE:
                            for (int i = 0; i < totalMembers; i++) {
                                splits.add(new PercentageSplit(userService.getUser(commands[4 + i]), Double.parseDouble(commands[expenseIndex + i + 1])));
                            }
                            splitWiseService.addExpense(PERCENTAGE, amountSpend, userName, splits, new ExpenseData("Dinner"));
                            break;
                    }
                    break;
                case SHOW:
                    if (commands.length == 1) {
                        splitWiseService.showBalances();
                    } else {
                        splitWiseService.showBalanceByUserName(commands[1]);
                    }
                    break;
                case QUIT:
                    System.out.println("Completed....");
                    break;
                default:
                    System.out.println("No Expected Argument found");
                    break;
            }
        }
    }
}
