package com.example.lld.practise.LLD_Questions.SplitWise.OwnCode.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group {
    String name;
    List<User> users;

    public Group(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }
}
