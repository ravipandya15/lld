package com.example.lld.practise.LLD_Questions.Journey_Framework.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UserJourney {
    private final String userId;
    private final String journeyId;
    private String currentStage;
}
