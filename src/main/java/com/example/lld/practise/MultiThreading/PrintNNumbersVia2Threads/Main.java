package com.example.lld.practise.MultiThreading.PrintNNumbersVia2Threads;

public class Main {

    public static void main(String[] args) {

        State state = new State(PrinterType.ODD);
        int maxvalue = 50;
        Printer oddPrinter = new Printer(1, 2, state, PrinterType.ODD, PrinterType.EVEN, maxvalue);
        Printer evenPrinter = new Printer(2, 2, state, PrinterType.EVEN, PrinterType.ODD, maxvalue);

        Thread oddThread = new Thread(oddPrinter, "ODD_PRINTER");
        Thread evenThread = new Thread(evenPrinter, "EVEN_PRINTER");

        oddThread.start();
        evenThread.start();
    }
}
