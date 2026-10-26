package com.example.lld.practise.LLD_Questions.DigitalWallet.model;

import com.example.lld.practise.LLD_Questions.DigitalWallet.Util.AccountNumberGenerator;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

@Data
public class Account {

    /*
            Input : double a=0.03;
                double b=0.04;
                double c=b-a;
                System.out.println(c);
            Output :0.009999999999999998

            Input : BigDecimal _a = new BigDecimal("0.03");
            BigDecimal _b = new BigDecimal("0.04");
            BigDecimal _c = _b.subtract(_a);
                System.out.println(_c);
            Output :0.01
     */


//    class name can be wallet instead of account
    private int accountNumber;
    private User user;
    private BigDecimal balance; // important to have balance as big decimal.
    private Set<Transaction> transactions;

    public Account(String userName, BigDecimal balance) {
        this.accountNumber = AccountNumberGenerator.getNextAccountNumber();
        this.user = new User(userName);
        this.balance = balance;
        this.transactions = new TreeSet<>((a, b) -> a.getDate().compareTo(b.getDate()));
    }
}
