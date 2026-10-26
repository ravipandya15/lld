package com.example.lld.practise.LLD_Questions.Journey_Framework.Model;

import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.ErrorCode;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyState;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.JourneyType;
import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.StageType;
import com.example.lld.practise.LLD_Questions.Journey_Framework.exception.JourneyException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class Journey {
    private final String id;
    private final String name;
    private final JourneyType type;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    @Setter
    private JourneyState state = JourneyState.CREATED;

    @ToString.Exclude
    private final Map<String, Stage> stages = new LinkedHashMap<>();
    @ToString.Exclude
    private final List<Transition> transitions = new ArrayList<>();

    public void addStage(String name, StageType type) {
        stages.put(name, new Stage(name, type));
    }

    public void addTransition(String from, String to, String rule) {
        transitions.add(new Transition(from, to, rule));
    }

    public String getOnboardingStage() {
        return stages.values().stream()
                .filter(s -> s.getType() == StageType.PREONBOARDING || s.getType() == StageType.ONBOARDING)
                .map(Stage::getName)
                .findFirst()
                .orElseThrow(() -> new JourneyException(ErrorCode.INVALID_JOURNEY_DEFINITION, "No entry stage found"));
    }

    public boolean isTerminal(String stageName) {
        Stage s = stages.get(stageName);
        return s != null && s.getType() == StageType.TERMINAL;
    }

    public List<Transition> transitionsFrom(String stage) {
        List<Transition> list = new ArrayList<>();
        for (Transition t : transitions)
            if (t.getFrom().equals(stage)) list.add(t);
        return list;
    }

    public boolean isValidityExpired() {
        return type == JourneyType.TIME_BOUND && LocalDateTime.now().isAfter(endTime);
    }
}
