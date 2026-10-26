package com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String id;
    private String playerName;
    private int currentPosition;

    public Player(String id, String playerName) {
        this.id = id;
        this.playerName = playerName;
        this.currentPosition = 0;
    }

}
