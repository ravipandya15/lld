package com.example.lld.practise.designPatterns.CommandDesignPattern.after;

public class TurnOnACCommand implements ICommand {
    AirConditioner ac;

    TurnOnACCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOnAC();
        ac.setTempreture(24);
    }

    @Override
    public void undo() {
        ac.turnOff();
    }
}
