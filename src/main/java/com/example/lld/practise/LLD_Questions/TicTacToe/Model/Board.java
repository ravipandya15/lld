package com.example.lld.practise.LLD_Questions.TicTacToe.Model;

import lombok.Getter;

@Getter
public class Board {
    private final int size;
    private final Piece[][] board;

    public Board(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    // can I use hashMap, as instead of iterating over it, I can check if map.size() == m*n then no free spot is available
    public boolean isFreeSpaceAvailable() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) return true;
            }
        }
        return false;
    }

    public boolean addPiece(int row, int col, Piece piece) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (board[row][col] != null) return false;
        board[row][col] = piece;
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print("   ");
                } else {
                    System.out.print(board[i][j].getPieceType().name() + "  ");
                }
                if (j != size - 1) System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
