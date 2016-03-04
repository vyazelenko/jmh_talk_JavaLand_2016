package com.vyazelenko;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ListGetBenchmarkJMH {
    private List<Integer> list;
    private int index;

    @Setup
    public void setUp() {
        list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        index = 3;
    }

    @Benchmark
    public void baseline() {
    }

    @Benchmark
    public Integer get() {
        return list.get(index);
    }
}
