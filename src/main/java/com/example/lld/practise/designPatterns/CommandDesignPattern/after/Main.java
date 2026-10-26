package com.example.lld.practise.designPatterns.CommandDesignPattern.after;

public class Main {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        MyRemoteControl remoteControl = new MyRemoteControl();

        remoteControl.setCommand(new TurnOnACCommand(airConditioner));
        remoteControl.pressButton();

        // undo the last operation
        remoteControl.undo();


    }
}
