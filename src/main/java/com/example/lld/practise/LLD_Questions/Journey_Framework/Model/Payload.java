package com.example.lld.practise.LLD_Questions.Journey_Framework.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Payload {
    private final Map<String, Object> data;

    public Object get(String key) {
        return data.get(key);
    }
}
