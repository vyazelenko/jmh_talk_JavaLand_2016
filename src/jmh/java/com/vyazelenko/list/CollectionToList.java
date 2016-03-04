package com.vyazelenko.list;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class CollectionToList {
    @Param({"ArrayList", "HashSet"})
    private String collection;

    private Collection<Object> data;

    @Setup
    public void setUp() throws Exception {
        data = (Collection<Object>) Class.forName("java.util." + collection).newInstance();
        for (int i = 0; i < 10_000; i++) {
            data.add(new Object());
        }
    }

    @Benchmark
    public ArrayList<Object> new_ArrayList_from_Collection() {
        return new ArrayList<>(data);
    }

    @Benchmark
    public ArrayList<Object> new_ArrayList_addAll() {
        ArrayList<Object> result = new ArrayList<>(data.size());
        result.addAll(data);
        return result;
    }

    @Benchmark
    public ArrayList<Object> new_ArrayList_add() {
        ArrayList<Object> result = new ArrayList<>(data.size());
        for (Object o : data) {
            result.add(o);
        }
        return result;
    }
}
