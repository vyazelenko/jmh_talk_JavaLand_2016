package com.vyazelenko.map;

import org.openjdk.jmh.annotations.*;
import water.nbhm.NonBlockingHashMapLong;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class LongKeyMap {
    private ConcurrentHashMap<Long, Double> chm;
    private NonBlockingHashMapLong<Double> nbhm;
    private long key;

    @Setup
    public void setUp() {
        chm = new ConcurrentHashMap<>();
        nbhm = new NonBlockingHashMapLong<>();
        long[] data = generate(50_000);
        populate(chm, data);
        populate(nbhm, data);
        key = 6528321900546851391L;
    }

    private void populate(Map<Long, Double> map, long[] data) {
        for (long key : data) {
            map.put(key, Double.longBitsToDouble(key));
        }
        if (map.size() != data.length) {
            throw new AssertionError();
        }
    }

    private long[] generate(int size) {
        Random r = new Random(-7890);
        long[] result = new long[size];
        for (int i = 0; i < size; i++) {
            result[i] = Math.abs(r.nextLong());
        }
        return result;
    }

    @Benchmark
    public Double get_ConcurrentHashMap() {
        return chm.get(key);
    }

    @Benchmark
    public Double get_NonBlockingHashMapLong() {
        return nbhm.get(key);
    }
}
