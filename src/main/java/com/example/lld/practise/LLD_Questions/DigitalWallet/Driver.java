package com.example.lld.practise.LLD_Questions.DigitalWallet;

import com.example.lld.practise.LLD_Questions.DigitalWallet.service.WalletService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        WalletService walletService = new WalletService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("OPTIONS:");
            System.out.println("1. Create wallet");
            System.out.println("2. Transfer Amount");
            System.out.println("3. Account Statement");
            System.out.println("4. Overview");
            System.out.println("5. Exit");

            switch (sc.nextInt()) {
                case 1:
                    System.out.println("You selected create wallet");
                    System.out.println("Enter Name");
                    String name = sc.next();
                    System.out.println("Enter Amount");
                    BigDecimal amount = sc.nextBigDecimal(); // IMP
                    walletService.createAccount(name, amount);
                    break;
                case 2:
                    System.out.println("You selected Transfer");
                    System.out.println("Enter Sender Account Number");
                    int fromAccountNumber = sc.nextInt();
                    System.out.println("Enter Receiver Account Number");
                    int toAccountNumber = sc.nextInt();
                    System.out.println("Enter Amount");
                    BigDecimal transferAmount = sc.nextBigDecimal();
                    walletService.transfer(fromAccountNumber, toAccountNumber, transferAmount);
                    break;
                case 3:
                    System.out.println("You selected Account Statement");
                    System.out.println("Enter Account Number");
                    int accountNumber = sc.nextInt();
                    walletService.generateStatement(accountNumber);
                    break;
                case 4:
                    walletService.overview();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("You selected invalid option, Please Reenter");
                    break;
            }
        }
    }
}
