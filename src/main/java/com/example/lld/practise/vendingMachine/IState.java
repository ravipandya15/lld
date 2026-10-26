package com.example.lld.practise.vendingMachine;

public interface IState {
    void pressInsertCoinButton();
    void insertCoin();
    void selectProductButton();
    void chooseProduct();
    void cancelOrder();
    void dispenseProduct();
    void updateInventory();
}
