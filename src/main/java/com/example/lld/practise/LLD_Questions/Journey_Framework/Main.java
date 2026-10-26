package com.example.lld.practise.LLD_Questions.Journey_Framework;

import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Journey;
import com.example.lld.practise.LLD_Questions.Journey_Framework.Model.Payload;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyState;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyType;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.StageType;
import com.example.lld.practise.LLD_Questions.Journey_Framework.exception.JourneyException;
import com.example.lld.practise.LLD_Questions.Journey_Framework.service.JourneyService;

import java.time.LocalDateTime;
import java.util.Map;

public class Main {

    static void main() {
        JourneyService service = JourneyService.getInstance();

        Journey phonepe = new Journey(
                "PHONEPE_RECHARGE",
                "PhonePe Recharge Funnel",
                JourneyType.TIME_BOUND,
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now().plusMinutes(10)
        );

        phonepe.addStage("Stage_PREONBOARD", StageType.PREONBOARDING);
        phonepe.addStage("Stage_A_Registered", StageType.ONBOARDING);
        phonepe.addStage("Stage_B_Recharge_Page", StageType.INTERMEDIATE); // renamed from ONWARD
        phonepe.addStage("Stage_C_Recharged", StageType.TERMINAL);

        phonepe.addTransition("Stage_PREONBOARD", "Stage_A_Registered", "event == REGISTER");
        phonepe.addTransition("Stage_A_Registered", "Stage_B_Recharge_Page", "event == OPEN_RECHARGE_PAGE");
        phonepe.addTransition("Stage_B_Recharge_Page", "Stage_C_Recharged", "event == DO_RECHARGE");

        service.createJourney(phonepe);
        service.updateState("PHONEPE_RECHARGE", JourneyState.ACTIVE);
        System.out.println("Journey created and ACTIVE\n");

        String user = "user1";

        // Action 1: User registers
        System.out.println("Action 1: User registering");
        service.evaluate(user, new Payload(Map.of("event", "REGISTER")));
        System.out.println("Onboarded? " + service.isOnboarded(user, "PHONEPE_RECHARGE"));
        System.out.println("Stage: " + service.getCurrentStage(user, "PHONEPE_RECHARGE") + "\n");
        System.out.println("Journey state: " + service.getJourney("PHONEPE_RECHARGE").getState() + "\n");

        // Action 2: User opens recharge page
        System.out.println("Action 2: User opens recharge page");
        service.evaluate(user, new Payload(Map.of("event", "OPEN_RECHARGE_PAGE")));
        System.out.println("Stage: " + service.getCurrentStage(user, "PHONEPE_RECHARGE") + "\n");
        System.out.println("Journey state: " + service.getJourney("PHONEPE_RECHARGE").getState() + "\n");

        // Action 3: User does recharge
        System.out.println("Action 3: User does recharge");
        service.evaluate(user, new Payload(Map.of("event", "DO_RECHARGE")));
        System.out.println("Stage: " + service.getCurrentStage(user, "PHONEPE_RECHARGE"));
        System.out.println("Journey state: " + service.getJourney("PHONEPE_RECHARGE").getState() + "\n");

        // Irrelevant event
        System.out.println("User opens wallet (irrelevant)");
        service.evaluate(user, new Payload(Map.of("event", "OPEN_WALLET")));
        System.out.println("Stage unchanged: " + service.getCurrentStage(user, "PHONEPE_RECHARGE") + "\n");

        // Error case
        System.out.println("Error case: unknown user");
        try {
            service.getCurrentStage("ghost", "PHONEPE_RECHARGE");
        } catch (JourneyException e) {
            System.out.println("Caught: " + e);
        }
    }
}
