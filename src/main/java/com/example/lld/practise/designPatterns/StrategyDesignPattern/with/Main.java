package com.example.lld.practise.designPatterns.StrategyDesignPattern.with;

public class Main {
    public static void main(String[] args) {
        PassengerVehical ps = new PassengerVehical();
        ps.drive();

        SportsVehical sv = new SportsVehical();
        sv.drive();
    }
}
