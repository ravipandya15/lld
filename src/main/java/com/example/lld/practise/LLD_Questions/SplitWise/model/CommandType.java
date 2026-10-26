package com.example.lld.practise.LLD_Questions.SplitWise.model;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    EXPENSE("EXPENSE"),
    SHOW("SHOW"),
    QUIT("QUIT");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    private static final Map<String, CommandType> map = new HashMap<>();

    static {
        for (CommandType type: values()) {
            map.put(type.name, type);
        }
    }

    public static CommandType of(String name) {
        return map.get(name);
    }
}
