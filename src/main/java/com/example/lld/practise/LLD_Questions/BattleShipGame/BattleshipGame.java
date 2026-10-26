package com.example.lld.practise.LLD_Questions.BattleShipGame;

import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.Orientation;

public class BattleshipGame {
    public static void main(String[] args) {
        Player p1 = new Player("Player 1", 5);
        Player p2 = new Player("Player 2", 5);

        // Example ship placements. Coordinates are zero-based.
        p1.placeShip(new Ship("Destroyer", 2),
                0, 0, Orientation.HORIZONTAL);
        p1.placeShip(new Ship("Submarine", 3),
                2, 1, Orientation.VERTICAL);

        p2.placeShip(new Ship("Destroyer", 2),
                1, 1, Orientation.HORIZONTAL);
        p2.placeShip(new Ship("Submarine", 3),
                3, 0, Orientation.HORIZONTAL);

        Game game = new Game(p1, p2);
        game.start();
    }
}
