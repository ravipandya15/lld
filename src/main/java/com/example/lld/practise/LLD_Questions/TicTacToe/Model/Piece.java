package com.example.lld.practise.LLD_Questions.TicTacToe.Model;

import lombok.Getter;

@Getter
public abstract class Piece {

    private final PieceType pieceType;
    public Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
