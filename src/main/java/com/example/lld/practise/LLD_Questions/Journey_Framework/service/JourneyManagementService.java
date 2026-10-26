package com.example.lld.practise.LLD_Questions.Journey_Framework.service;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Journey;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.ErrorCode;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.StageType;
import com.example.lld.practise.LLD_Questions.Journey_Framework.exception.JourneyException;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyManagementInterface;

import java.util.Map;

public class JourneyManagementService implements JourneyManagementInterface {
    private final Map<String, Journey> journeys;

    public JourneyManagementService(Map<String, Journey> journeys) {
        this.journeys = journeys;
    }

    @Override
    public void createJourney(Journey journey) {
        if (journey == null || journey.getId() == null) {
            throw new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION, "Journey is null");
        }
        if (journeys.containsKey(journey.getId())) {
            throw new JourneyException(ErrorCode.DUPLICATE_JOURNEY, "Journey already exists: " + journey.getId());
        }
        validate(journey);
        journeys.put(journey.getId(), journey);
    }

    @Override
    public Journey getJourney(String journeyId) {
        Journey journey = journeys.get(journeyId);
        if (journey == null) {
            throw new JourneyException(ErrorCode.JOURNEY_NOT_FOUND, "Journey not found: " + journeyId);
        }
        return journey;
    }

    private void validate(Journey journey) {
        if (journey.getStages().isEmpty())
            throw new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION, "No stages");

        long preboarding = journey.getStages().values().stream()
                .filter(s -> s.getType() == StageType.PREONBOARDING).count();
        long onboarding = journey.getStages().values().stream()
                .filter(s -> s.getType() == StageType.ONBOARDING).count();
        long terminal = journey.getStages().values().stream()
                .filter(s -> s.getType() == StageType.TERMINAL).count();

        if (preboarding > 1) throw new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION,
                "Journey can have at most 1 PREONBOARDING stage");
        if (onboarding > 1) throw new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION,
                "Journey can have at most 1 ONBOARDING stage");
        if (preboarding == 0 && onboarding == 0) throw new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION,
                "Journey must have at least 1 ONBOARDING or PREONBOARDING stage");
        if (terminal != 1) throw new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION,
                "Journey must have exactly 1 TERMINAL stage");
    }
}
