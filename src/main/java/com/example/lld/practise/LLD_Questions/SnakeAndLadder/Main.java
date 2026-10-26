package com.example.lld.practise.LLD_Questions.SnakeAndLadder;

import com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Player> players = new LinkedList<>();
        Player player1 = new Player("1", "Ravi");
        Player player2 = new Player("2", "Raj");
        players.add(player1);
        players.add(player2);

        Dice dice = new Dice(2);

        List<Jumper> snakes = new ArrayList<>();
        Jumper snake1 = new Snake(10, 2);
        Jumper snake2 = new Snake(50, 35);
        Jumper snake3 = new Snake(99, 12);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);

        List<Jumper> ladders = new ArrayList<>();
        Jumper ladder1 = new Ladder(4, 25);
        Jumper ladder2 = new Ladder(6, 25);
        Jumper ladder3 = new Ladder(9, 25);
        Jumper ladder4 = new Ladder(5, 25);
        Jumper ladder5 = new Ladder(19, 30);
        Jumper ladder6 = new Ladder(40, 89);
        ladders.add(ladder1);
        ladders.add(ladder2);
        ladders.add(ladder3);
        ladders.add(ladder4);
        ladders.add(ladder5);
        ladders.add(ladder6);


        Board board = new Board(100, dice, snakes, ladders);
        SnakeLadderGame snakeLadderGame = new SnakeLadderGame(players, board);
        snakeLadderGame.playGame();
    }
}
