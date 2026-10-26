package com.example.lld.practise.designPatterns.FlyweightDesignPattern.WordProcessor.Solution;

public class Main {

    public static void main(String[] args) {
        /*
            this is the data we want to write into the work processor.

            Total: 58 characters
            t = 7 times
            h = 3 times
            a = 3 times and so on....
         */


        ILetter letter1 = LetterFactory.createLetter('t');
        letter1.display(0,0);

        ILetter letter5 = LetterFactory.createLetter('t');
        letter5.display(0,8);

        ILetter letter2 = LetterFactory.createLetter('h');
        letter2.display(0,1);

        ILetter letter3 = LetterFactory.createLetter('i');
        letter3.display(0,3);

        ILetter letter4 = LetterFactory.createLetter('s');
        letter4.display(0,4);
    }
}
