package com.example.lld.practise.MultiThreading.PrintNNumbersVia2Threads;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class State {

    private PrinterType nextToPrint;

    public State(@NonNull final PrinterType printerType) {
        this.nextToPrint = printerType;
    }
}