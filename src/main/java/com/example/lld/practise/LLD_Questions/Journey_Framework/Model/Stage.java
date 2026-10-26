package com.example.lld.practise.LLD_Questions.Journey_Framework.Model;

import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.StageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Stage {
    private final String name;
    private final StageType type;
}
