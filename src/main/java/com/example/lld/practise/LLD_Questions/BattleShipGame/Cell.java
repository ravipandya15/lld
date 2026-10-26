package com.example.lld.practise.LLD_Questions.BattleShipGame;

import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.AttackResult;
import com.example.lld.practise.LLD_Questions.BattleShipGame.enums.CellStatus;

public class Cell {
    private Ship ship;
    private CellStatus status = CellStatus.EMPTY;

    public boolean hasShip() {
        return ship != null;
    }

    public boolean isAttacked() {
        return status == CellStatus.HIT || status == CellStatus.MISS;
    }

    public void placeShip(Ship ship) {
        this.ship = ship;
        this.status = CellStatus.SHIP;
    }

    public AttackResult receiveAttack() {
        if (isAttacked()) {
            return AttackResult.ALREADY_ATTACKED;
        }

        if (ship == null) {
            status = CellStatus.MISS;
            return AttackResult.MISS;
        }

        status = CellStatus.HIT;
        ship.hit();

        return ship.isSunk()
                ? AttackResult.SUNK
                : AttackResult.HIT;
    }

    public String display(boolean revealShips) {
        if (status == CellStatus.HIT) return "X";
        if (status == CellStatus.MISS) return "O";
        if (revealShips && ship != null) return "S";
        return ".";
    }
}
