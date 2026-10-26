package com.example.lld.practise.LLD_Questions.SplitWise.model;

import java.util.HashMap;
import java.util.Map;

public enum ExpenseType {

    EQUAL("EQUAL"),
    EXACT("EXACT"),
    PERCENTAGE("PERCENTAGE");

    private final String expense;

    ExpenseType(String expense) {
        this.expense = expense;
    }

    public String toString() {
        return this.expense;
    }

    private static final Map<String, ExpenseType> map = new HashMap<>();

    static {
        for (ExpenseType type: values()) {
            map.put(type.expense, type);
        }
    }

    public static ExpenseType of(String name) {
        return map.get(name);
    }
}
