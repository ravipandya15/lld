package com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Setter
@Getter
public class Dice {
    private int count;

    public Dice(int count) {
        this.count = count;
    }

//    0.0 <= Math.random() < 1.0 -> gives answer in double
    public int rollDice() {
//        int sum = 0;
//        for (int i = 0; i < count; i++) {
//            sum += (int)(Math.random() * 6) + 1; // this will give [1,7) where 1 -> inclusive and 7 -> exclusive
//        }
//        return sum;

//        return IntStream.range(0, count) // -> count = 3 -> loop from 0 ,1,2
//                .map(i -> (int)(Math.random() * count) + 1)
//                .sum();

        // ThreadLocalRandom -> for multiThreaded application

        return IntStream.range(0, count)
                .map(i -> ThreadLocalRandom.current().nextInt(1, 7))
                .sum();
    }
}
