package com.example.lld.practise.LLD_Questions.DigitalWallet.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transaction {
    private int fromAccountNumber;
    private int toAccountNumber;
    private BigDecimal amount;
    private Date date;

    public Transaction(int fromAccountNumber, int toAccountNumber, BigDecimal amount, Date date) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
