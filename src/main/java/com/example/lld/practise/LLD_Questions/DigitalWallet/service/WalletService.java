package com.example.lld.practise.LLD_Questions.DigitalWallet.service;

import com.example.lld.practise.LLD_Questions.DigitalWallet.dao.WalletDao;
import com.example.lld.practise.LLD_Questions.DigitalWallet.model.Account;
import com.example.lld.practise.LLD_Questions.DigitalWallet.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class WalletService {
    private WalletDao walletDao;

    public WalletService() {
        this.walletDao = new WalletDao();
    }

    public void createAccount(String userName, BigDecimal amount) {
        Account account = new Account(userName, amount);
        walletDao.getAccountMap().put(account.getAccountNumber(), account);
        System.out.println("Account created for user " + userName + " with account Number " + account.getAccountNumber());
    }

    public void transfer(int fromAccountNumber, int toAccountNumber, BigDecimal transferAmount) {
        if (!validate(fromAccountNumber, toAccountNumber, transferAmount)) {
            return;
        }
        Account fromAccount = walletDao.getAccountMap().get(fromAccountNumber);
        Account toAccount = walletDao.getAccountMap().get(toAccountNumber);

        if (fromAccount.getBalance().compareTo(transferAmount) < 0) {
            System.out.println("Insufficient balance in account: " + fromAccountNumber);
            return;
        }
        Transaction transaction = new Transaction(fromAccountNumber, toAccountNumber, transferAmount, new Date());

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferAmount));
        toAccount.setBalance(toAccount.getBalance().add(transferAmount));
        fromAccount.getTransactions().add(transaction);
        toAccount.getTransactions().add(transaction);
        System.out.println("Transfer successfully completed");
    }

    public void generateStatement(int accountNumber) {
        Account account = walletDao.getAccountMap().get(accountNumber);
        if (account == null) {
            System.out.println("Invalid Account Number");
            return;
        }
        System.out.println("Summary for account number: " + accountNumber);
        System.out.println("current Balance: " + account.getBalance());
        System.out.println("Your Transaction History");
        System.out.println(account.getTransactions());
    }

    public void overview() {
        for (Map.Entry<Integer, Account> account: walletDao.getAccountMap().entrySet()) {
            System.out.println("Balance for accountNumber " + account.getKey() + ": " + account.getValue().getBalance());
        }
    }

    private boolean validate(int fromAccount, int toAccount, BigDecimal transferAmount) {
        if (fromAccount == toAccount) {
            System.out.println("Sender and Receiver cannot be same");
            return false;
        }
        if (transferAmount.compareTo(new BigDecimal("0.0001")) < 0){
            System.out.println("Amount is too low");
            return false;
        }
        if (!walletDao.getAccountMap().containsKey(fromAccount)) {
            System.out.println("Invalid Sender account number");
            return false;
        }
        if (!walletDao.getAccountMap().containsKey(toAccount)) {
            System.out.println("Invalid Receiver account number");
            return false;
        }
        return true;
    }
}
