package com.example.lld.practise.LLD_Questions.Journey_Framework.service;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Payload;
import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Transition;
import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.UserJourney;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyState;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyEvaluationInterface;
import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Journey;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.ErrorCode;
import com.example.lld.practise.LLD_Questions.Journey_Framework.exception.JourneyException;

import java.util.Map;

public class JourneyEvaluationService implements JourneyEvaluationInterface {
    private final Map<String, Journey> journeys;
    private final Map<String, UserJourney> userJourneys;

    public JourneyEvaluationService(Map<String, Journey> journeys,
                                    Map<String, UserJourney> userJourneys) {
        this.journeys = journeys;
        this.userJourneys = userJourneys;
    }

    private String getkey(String userId, String journeyId) {
        return userId + "::" + journeyId;
    }

    @Override
    public void evaluate(String userId, Payload payload) {
        if (userId == null) {
            throw new JourneyException(ErrorCode.INVALID_USER, "userId required");
        }

        if (payload == null) {
            throw new JourneyException(ErrorCode.INVALID_PAYLOAD, "payload required");
        }

        for (Journey journey : journeys.values()) {
            if (journey.getState() != JourneyState.ACTIVE) continue;

            if (journey.isValidityExpired()) {
                journey.setState(JourneyState.EXPIRED);
                continue;
            }

            String key = getkey(userId, journey.getId());
            UserJourney userJourney = userJourneys.get(key);

            if (userJourney == null) {
                String entryStage = journey.getOnboardingStage();
                UserJourney newUserJourney = new UserJourney(userId, journey.getId(), entryStage);
                userJourneys.put(key, newUserJourney);

                for (Transition transition : journey.transitionsFrom(entryStage)) {
                    if (transition.matches(payload)) {
                        newUserJourney.setCurrentStage(transition.getTo());
                        break;
                    }
                }
            } else {
                if (journey.isTerminal(userJourney.getCurrentStage())) continue;

                for (Transition t : journey.transitionsFrom(userJourney.getCurrentStage())) {
                    if (t.matches(payload)) {
                        userJourney.setCurrentStage(t.getTo());
                        if (journey.isTerminal(t.getTo())) {
                            journey.setState(JourneyState.COMPLETED);
                        }
                        break;
                    }
                }
            }
        }
    }
}
