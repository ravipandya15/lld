package com.example.lld.practise.LLD_Questions.TicTacToe;

import com.example.lld.practise.LLD_Questions.TicTacToe.Model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {

    Deque<Player> players;
    Board gameBoard;

    public TicTacToeGame(int playerCount, int boardSize) {
//            Scanner inputScanner = new Scanner(System.in);
        players = new LinkedList<>(); // --> new ArrayDeque<>();ßßß
        PieceX crossPiece = new PieceX(PieceType.X);
        Player p1 = new Player("Player1", crossPiece);

        PieceO noughtPiece = new PieceO(PieceType.O);
        Player p2 = new Player("Player2", noughtPiece);

        players.add(p1);
        players.add(p2);

        gameBoard = new Board(3);
    }

    public String playGame() {

        while (true) {
            Player currentPlayer = players.pollFirst();

            if (!gameBoard.isFreeSpaceAvailable()) break;

            gameBoard.printBoard();
            assert currentPlayer != null;
            System.out.println("Player: " + currentPlayer.getName() + " Enter row, col: ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");
            int row = Integer.parseInt(values[0]);
            int col = Integer.parseInt(values[1]);


            if (!gameBoard.addPiece(row, col, currentPlayer.getPiece())) {
                System.out.println("Incorrect position, try again");
                players.addFirst(currentPlayer);
                continue;
            }

            if (isWinner(row, col, currentPlayer.getPiece().getPieceType())) {
                return currentPlayer.getName();
            }

            players.addLast(currentPlayer);
        }
        return "Tie";
    }

    private boolean isWinner(int row, int col, PieceType pieceType) {

        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalmatch = true;
        boolean antiDiagonal = true;

        Piece[][] pieces = gameBoard.getBoard();
        for (int j = 0; j < gameBoard.getSize(); j++) {
            if (pieces[row][j] == null || pieces[row][j].getPieceType() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        for (int i = 0; i < gameBoard.getSize(); i++) {
            if (pieces[i][col] == null || pieces[i][col].getPieceType() != pieceType) {
                colMatch = false;
                break;
            }
        }

        for (int i = 0, j = 0; i < gameBoard.getSize(); i++, j++) {
            if (pieces[i][j] == null || pieces[i][j].getPieceType() != pieceType) {
                diagonalmatch = false;
                break;
            }
        }

        for (int i = 0, j = gameBoard.getSize()-1; i < gameBoard.getSize(); i++, j--) {
            if (pieces[i][j] == null || pieces[i][j].getPieceType() != pieceType) {
                antiDiagonal = false;
                break;
            }
        }

        if (rowMatch || colMatch || diagonalmatch || antiDiagonal) return true;

        return false;
    }
}
