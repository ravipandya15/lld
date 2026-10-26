package com.example.lld.practise.MultiThreading.PrintNNumbersVia2Threads;

import lombok.NonNull;
import lombok.SneakyThrows;

public class Printer implements Runnable {

    private int currentValue;
    private final int step;
    private final State state;
    private final PrinterType currentPrinterType;
    private final PrinterType nextPrinterType;
    private final int maxvalue;

    public Printer(@NonNull final int currentValue, @NonNull final int step, @NonNull final State state,
                   @NonNull final PrinterType currentPrinterType, @NonNull final PrinterType nextPrinterType, @NonNull final int maxvalue) {
        this.currentValue = currentValue;
        this.step = step;
        this.state = state;
        this.currentPrinterType = currentPrinterType;
        this.nextPrinterType = nextPrinterType;
        this.maxvalue = maxvalue;
    }
//    public Printer(@NonNull final int currentValue, @NonNull final int step, @NonNull final State state,
//                   @NonNull final PrinterType currentPrinterType, @NonNull final PrinterType nextPrinterType, @NonNull final int maxvalue) {
////        this.currentValue = currentValue;
////        this.step = step;
//        this.state = state;
////        this.currentPrinterType = currentPrinterType;
////        this.nextPrinterType = nextPrinterType;
////        this.maxvalue = maxvalue;
//    }


    @Override
    @SneakyThrows
    public void run() {
        while (currentValue <= maxvalue) {
            synchronized (state) { // synchronized (this.state)
                // here very important point, when we execute state.wait(), it will first release the lock before waiting, so any other thread who wants
                // to enter into synchronized block can enter it and perform execution.
                if (currentPrinterType != state.getNextToPrint()) { //--> while will also work
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(currentPrinterType + ": " + currentValue);
                currentValue += step;
                state.setNextToPrint(nextPrinterType);
                Thread.sleep(400);
                state.notifyAll();
            }
        }
    }

//        @Override
//        public void run() {
//            while (currentValue <= maxvalue) {
//                synchronized (state) {
//                if (currentValue % 2 == 0)
//                    try {
//                        state.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println(Thread.currentThread().getName() + ": "+ currentValue);
//                currentValue++;
////                state.setNextToPrint(nextPrinterType);
//                state.notifyAll();
//            }
//        }
    }
