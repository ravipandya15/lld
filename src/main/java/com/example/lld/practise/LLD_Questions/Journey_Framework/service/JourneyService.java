package com.example.lld.practise.LLD_Questions.Journey_Framework.service;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Journey;
import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Payload;
import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.UserJourney;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyState;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyEvaluationInterface;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyManagementInterface;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.JourneyStateInterface;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces.UserProgressInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JourneyService implements JourneyManagementInterface,
                                        JourneyStateInterface,
                                        UserProgressInterface,
                                        JourneyEvaluationInterface {

    private static volatile JourneyService INSTANCE;

    private final JourneyManagementInterface journeyManagementInterface;
    private final JourneyStateInterface journeyStateInterface;
    private final UserProgressInterface userProgressInterface;
    private final JourneyEvaluationInterface journeyEvaluationInterface;

    private JourneyService() {
        Map<String, Journey> journeys = new ConcurrentHashMap<>();
        Map<String, UserJourney> userJourneys = new ConcurrentHashMap<>();

        this.journeyManagementInterface = new JourneyManagementService(journeys);
        this.journeyStateInterface = new JourneyStateService(journeyManagementInterface);
        this.userProgressInterface = new UserProgressService(userJourneys, journeyManagementInterface);
        this.journeyEvaluationInterface = new JourneyEvaluationService(journeys, userJourneys);
    }

    public static synchronized JourneyService getInstance() {
        if (INSTANCE == null) {
             INSTANCE = new JourneyService();
        }
        return INSTANCE;
    }

    @Override
    public void createJourney(Journey journey) {
        journeyManagementInterface.createJourney(journey);
    }

    @Override
    public Journey getJourney(String journeyId) {
        return journeyManagementInterface.getJourney(journeyId);
    }

    @Override
    public void updateState(String journeyId, JourneyState newState) {
        journeyStateInterface.updateState(journeyId, newState);
    }

    @Override
    public String getCurrentStage(String userId, String journeyId) {
        return userProgressInterface.getCurrentStage(userId, journeyId);
    }

    @Override
    public boolean isOnboarded(String userId, String journeyId) {
        return userProgressInterface.isOnboarded(userId, journeyId);
    }

    @Override
    public void evaluate(String userId, Payload payload) {
        journeyEvaluationInterface.evaluate(userId, payload);
    }
}
