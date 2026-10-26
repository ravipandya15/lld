package com.example.lld.practise.designPatterns.CommandDesignPattern.after;

public class TurnOffACCommand implements ICommand {
    AirConditioner ac;

    public TurnOffACCommand(AirConditioner ac) {
        this.ac = ac;
    }
    @Override
    public void execute() {
        ac.turnOff();
    }

    @Override
    public void undo() {
        ac.turnOnAC();
    }
}
