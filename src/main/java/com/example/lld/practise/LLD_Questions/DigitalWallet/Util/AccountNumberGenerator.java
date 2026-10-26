package com.example.lld.practise.LLD_Questions.DigitalWallet.Util;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountNumberGenerator {

    private static final AtomicInteger accountNumber = new AtomicInteger(0);

    public static int getNextAccountNumber() {
        return accountNumber.incrementAndGet();
    }
}
