package com.example.lld.practise.designPatterns.CommandDesignPattern.before;

public class AirConditioner {
    boolean isOn;
    int tempreture;

    public void turnOnAC() {
        this.isOn = true;
        System.out.println("AC is ON");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println("AC is Off");
    }

    public void setTempreture(int temp) {
        this.tempreture = temp;
        System.out.println("Temerature changed to: " + this.tempreture);
    }
}
