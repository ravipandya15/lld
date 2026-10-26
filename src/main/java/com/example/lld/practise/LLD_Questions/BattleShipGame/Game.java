package com.example.lld.practise.LLD_Questions.BattleShipGame;

import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.AttackResult;
import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.GameStatus;

import java.util.Scanner;

public class Game {
    private final Player[] players;
    private int currentPlayerIndex;
    private GameStatus status = GameStatus.NOT_STARTED;

    public Game(Player p1, Player p2) {
        players = new Player[]{p1, p2};
    }

    public void start() {
        status = GameStatus.IN_PROGRESS;
        currentPlayerIndex = 0;

        Scanner scanner = new Scanner(System.in);

        while (status == GameStatus.IN_PROGRESS) {
            Player current = players[currentPlayerIndex];
            Player opponent = players[1 - currentPlayerIndex];

            System.out.println("\n" + current.getName() + "'s turn");
            System.out.println("Your board:");
            current.getBoard().print(true);

            System.out.println("Enter attack coordinates: row col");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            try {
                AttackResult result = current.attack(opponent, row, col);
                System.out.println("Result: " + result);

                if (result == AttackResult.ALREADY_ATTACKED) {
                    System.out.println("Choose a different cell.");
                    continue;
                }

                if (opponent.getBoard().allShipsSunk()) {
                    status = GameStatus.FINISHED;
                    System.out.println(
                            current.getName() + " wins the game!");
                    break;
                }

                currentPlayerIndex = 1 - currentPlayerIndex;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
