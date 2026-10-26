package com.example.lld.practise.Coding_Questions.immutability;

import lombok.Data;

@Data
public final class User {
    private final String userId;
    private final String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    // copy constructor
    public User(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }
}
