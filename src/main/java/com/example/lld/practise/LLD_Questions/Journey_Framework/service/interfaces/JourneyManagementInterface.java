package com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Journey;

public interface JourneyManagementInterface {
    void createJourney(Journey journey);
    Journey getJourney(String journeyId);
}
