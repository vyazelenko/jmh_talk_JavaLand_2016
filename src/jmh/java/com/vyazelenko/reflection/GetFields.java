package com.vyazelenko.reflection;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class GetFields {
    private Class<?> klass;
    private ConcurrentMap<Class<?>, Field[]> fieldsCache;

    @Setup
    public void setUp() {
        klass = C.class;
        fieldsCache = new ConcurrentHashMap<>();
    }

    @Benchmark
    public Field[] getDeclaredFields() {
        return klass.getDeclaredFields();
    }

    @Benchmark
    public Field[] getDeclaredFields_cached() {
        Field[] result = fieldsCache.get(klass);
        if (result == null) {
            result = klass.getDeclaredFields();
            Field[] tmp = fieldsCache.putIfAbsent(klass, result);
            if (tmp != null) {
                return tmp;
            }
        }
        return result;
    }
}
