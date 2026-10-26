package com.example.lld.practise.LLD_Questions.Journey_Framework.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Transition {
    private final String from;
    private final String to;
    private final String rule;

    public boolean matches(Payload payload) {

        if (rule == null || rule.isBlank()) return true;

        int index = rule.indexOf("==");
        if (index == -1) return false;

        String key = rule.substring(0, index).trim();
        String expected = rule.substring(index + 2).trim();

        Object actual = payload.get(key);
        if (actual == null) return false;

        return actual.toString().equalsIgnoreCase(expected);
    }
}
