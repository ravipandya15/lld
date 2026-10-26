package com.example.lld.practise.LLD_Questions.SnakeAndLadder;

import com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model.Board;
import com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model.Player;

import java.util.Queue;

public class SnakeLadderGame {

    private final Queue<Player> players;

    private final Board board;

    public SnakeLadderGame(Queue<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    public void playGame() {
        boolean noWinner = true;
        while (noWinner) {
            Player currentPlayer = players.poll();
            int diceValue = board.getDice().rollDice();
            assert currentPlayer != null;
            System.out.println("Player: " + currentPlayer.getId() + ", Dice value: " + diceValue);

            int position = currentPlayer.getCurrentPosition() + diceValue;

            if (this.board.isAnyJump(position)) {
                position = this.board.findNextPosition(position);
            }
            System.out.println("Player: " + currentPlayer.getId() + ", updatedValue " + position);

            if (position > board.getSize()) {
                players.add(currentPlayer);
            } else if (position == board.getSize()) {
                System.out.println("Winner is: " + currentPlayer.getPlayerName());
                noWinner = false;
            } else {
                currentPlayer.setCurrentPosition(position);
                players.add(currentPlayer);
            }
        }
    }
}
