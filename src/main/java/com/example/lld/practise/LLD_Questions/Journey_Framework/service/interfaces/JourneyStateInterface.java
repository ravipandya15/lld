package com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces;

import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyState;

public interface JourneyStateInterface {
    void updateState(String journeyId, JourneyState newState);
}
