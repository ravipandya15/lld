package com.example.lld.practise.LLD_Questions.DigitalWallet.dao;

import com.example.lld.practise.LLD_Questions.DigitalWallet.model.Account;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WalletDao {
    private Map<Integer, Account> accountMap;

    public WalletDao() {
        this.accountMap = new HashMap<>();
    }
}
