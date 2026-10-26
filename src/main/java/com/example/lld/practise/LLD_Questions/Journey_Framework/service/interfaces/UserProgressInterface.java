package com.example.lld.practise.LLD_Questions.Journey_Framework.service.interfaces;

public interface UserProgressInterface {
    String getCurrentStage(String userId, String journeyId);
    boolean isOnboarded(String userId, String journeyId);
}
