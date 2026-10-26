package com.example.lld.practise.LLD_Questions.BattleShipGame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {
    private final String name;
    private final int size;
    private int hits;

    public Ship(String name, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Ship size must be positive");
        }
        this.name = name;
        this.size = size;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= size;
    }
}
