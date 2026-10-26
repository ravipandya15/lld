package com.example.lld.practise.LLD_Questions.Journey_Framework.service;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Journey;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.ErrorCode;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyState;
import com.example.lld.practise.LLD_Questions.Journey_Framework.exception.JourneyException;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyManagementInterface;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyStateInterface;

public class JourneyStateService implements JourneyStateInterface {
    private final JourneyManagementInterface management;

    public JourneyStateService(JourneyManagementInterface management) {
        this.management = management;
    }

    @Override
    public void updateState(String journeyId, JourneyState newState) {
        Journey journey = management.getJourney(journeyId);

        boolean allowed =  (journey.getState() == JourneyState.CREATED && newState == JourneyState.ACTIVE) ||
                            (journey.getState() == JourneyState.ACTIVE  && (newState == JourneyState.COMPLETED
                                || newState == JourneyState.EXPIRED));

        if (!allowed) {
            throw new JourneyException(ErrorCode.INVALID_JOURNEY_STATE,
                    "Cannot go from " + journey.getState() + " to " + newState);
        }
        journey.setState(newState);
    }
}
