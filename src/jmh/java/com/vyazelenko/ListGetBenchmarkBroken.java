package com.vyazelenko;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListGetBenchmarkBroken {
    private static final int ITERATIONS = 1_000_000_000;

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        long startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            list.get(3);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Executed in "
                + TimeUnit.NANOSECONDS.toMillis(duration) + "ms, " +
                +((double) duration / ITERATIONS) + " ns/op");
    }
}
