package com.example.lld.practise.LLD_Questions.SplitWise.service;

import com.example.lld.practise.LLD_Questions.SplitWise.model.User;
import com.example.lld.practise.LLD_Questions.SplitWise.repository.ExpenseRepository;

public class UserService {

    ExpenseRepository expenseRepository;

    public UserService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addUser(User user) {
        expenseRepository.addUser(user);
    }

    public User getUser(String userName) {
        return expenseRepository.getUser(userName);
    }
}
