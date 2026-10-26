package com.example.lld.practise.LLD_Questions.BattleShipGame;

import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.AttackResult;
import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.Orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final int size;
    private final Cell[][] cells;
    private final List<Ship> ships = new ArrayList<>();

    public Board(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be positive");
        }

        this.size = size;
        cells = new Cell[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c] = new Cell();
            }
        }
    }

    public void placeShip(
            Ship ship, int row, int col, Orientation orientation) {

        Objects.requireNonNull(ship);
        Objects.requireNonNull(orientation);

        if (row < 0 || col < 0 ||
                row >= size || col >= size) {
            throw new IllegalArgumentException("Invalid starting position");
        }

        int rowEnd = row +
                (orientation == Orientation.VERTICAL ? ship.getSize() - 1 : 0);
        int colEnd = col +
                (orientation == Orientation.HORIZONTAL ? ship.getSize() - 1 : 0);

        if (rowEnd >= size || colEnd >= size) {
            throw new IllegalArgumentException("Ship exceeds board boundaries");
        }

        // Validate all cells before modifying the board.
        for (int i = 0; i < ship.getSize(); i++) {
            int r = row + (orientation == Orientation.VERTICAL ? i : 0);
            int c = col + (orientation == Orientation.HORIZONTAL ? i : 0);

            if (cells[r][c].hasShip()) {
                throw new IllegalArgumentException("Ships cannot overlap");
            }
        }

        for (int i = 0; i < ship.getSize(); i++) {
            int r = row + (orientation == Orientation.VERTICAL ? i : 0);
            int c = col + (orientation == Orientation.HORIZONTAL ? i : 0);
            cells[r][c].placeShip(ship);
        }

        ships.add(ship);
    }

    public AttackResult receiveAttack(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IllegalArgumentException("Attack is outside the board");
        }

        return cells[row][col].receiveAttack();
    }

    public boolean allShipsSunk() {
        return !ships.isEmpty() && ships.stream().allMatch(Ship::isSunk);
    }

    public void print(boolean revealShips) {
        System.out.print("  ");
        for (int c = 0; c < size; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int r = 0; r < size; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < size; c++) {
                System.out.print(cells[r][c].display(revealShips) + " ");
            }
            System.out.println();
        }
    }
}
