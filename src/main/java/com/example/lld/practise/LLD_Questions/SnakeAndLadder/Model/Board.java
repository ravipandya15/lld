package com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    @Getter
    private final int size;
    @Getter
    private final Dice dice;
    private final List<Jumper> snakes;
    private final List<Jumper> ladders;

    @Getter
    private final Map<Integer, Integer> map;

    public Board(int size, Dice dice, List<Jumper> snakes, List<Jumper> ladders) {
        map = new HashMap<>();
        this.size = size;
        this.dice = dice;
        this.snakes = snakes;
        this.ladders = ladders;
        initializeMap();
    }

    private void initializeMap() {
        for (Jumper snake : this.snakes) {
            this.map.put(snake.getStartPosition(), snake.getEndPosition());
        }

        for (Jumper ladder : this.ladders) {
            this.map.put(ladder.getStartPosition(), ladder.getEndPosition());
        }
    }

    public boolean isAnyJump(int position) {

        if (map != null && map.containsKey(position)) {
            System.out.println("jump from " + position + " to " + map.get(position));
            return true;
        }
        return false;
    }

    public int findNextPosition(int startPosition) {
        return map.get(startPosition);
    }
}
