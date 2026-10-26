package com.example.lld.practise.LLD_Questions.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame(2, 3);
        String result = ticTacToeGame.playGame();
        if (result.equals("Tie")) {
            System.out.println("Game is tie");
        } else {
            System.out.println("Winner is: " + result);
        }
    }
}
