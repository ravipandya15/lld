package com.example.lld.practise.LLD_Questions.Journey_Framework.service;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.UserJourney;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.ErrorCode;
import com.example.lld.practise.LLD_Questions.Journey_Framework.exception.JourneyException;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyManagementInterface;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.UserProgressInterface;

import java.util.Map;

public class UserProgressService implements UserProgressInterface {
    private final Map<String, UserJourney> userJourneys;
    private final JourneyManagementInterface management;

    public UserProgressService(Map<String, UserJourney> userJourneys,
                               JourneyManagementInterface management) {
        this.userJourneys = userJourneys;
        this.management = management;
    }

    private String key(String userId, String journeyId) {
        return userId + "::" + journeyId;
    }

    @Override
    public String getCurrentStage(String userId, String journeyId) {
        management.getJourney(journeyId);
        UserJourney userJourney = userJourneys.get(key(userId, journeyId));
        if (userJourney == null) {
            throw new JourneyException(ErrorCode.USER_NOT_ONBOARDED,
                    "User " + userId + " is not onboarded to " + journeyId);
        }
        return userJourney.getCurrentStage();
    }

    @Override
    public boolean isOnboarded(String userId, String journeyId) {
        management.getJourney(journeyId);
        return userJourneys.containsKey(key(userId, journeyId));
    }
}
