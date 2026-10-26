package com.example.lld.practise.designPatterns.FlyweightDesignPattern.WordProcessor.Problem;

public class Main {

    public static void main(String[] args) {
        /*
            this is the data we want to write into the word processor.

            Total: 58 characters
            t = 7 times
            h = 3 times
            a = 3 times and so on....
         */

        Character ch1 = new Character('t', "Arial", 10, 0, 0);
        Character ch2 = new Character('h', "Arial", 10, 0, 1);
        Character ch3 = new Character('i', "Arial", 10, 0, 2);
        Character ch4 = new Character('s', "Arial", 10, 0, 3);

        // SO MANY OBJECTS I NEED TO CREATE
    }
}
