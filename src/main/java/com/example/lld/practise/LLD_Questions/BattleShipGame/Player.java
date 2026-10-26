package com.example.lld.practise.LLD_Questions.BattleShipGame;

import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.AttackResult;
import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.Orientation;

public class Player {
    private final String name;
    private final Board board;

    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void placeShip(
            Ship ship, int row, int col, Orientation orientation) {
        board.placeShip(ship, row, col, orientation);
    }

    public AttackResult attack(Player opponent, int row, int col) {
        return opponent.board.receiveAttack(row, col);
    }
}
