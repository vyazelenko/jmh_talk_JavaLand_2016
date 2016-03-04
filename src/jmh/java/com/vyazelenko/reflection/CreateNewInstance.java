package com.vyazelenko.reflection;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class CreateNewInstance {
    private Class<?> klass;
    private String className;
    private Constructor<?> contructor;
    private Date arg1;
    private String arg2;

    @Setup
    public void setUp() throws Exception {
        klass = C.class;
        className = klass.getName();
        contructor = klass.getConstructor(Date.class, String.class);
        arg1 = new Date();
        arg2 = arg1.toString() + " something else";
    }

    @Benchmark
    public Object load_class_find_constructor_allocate() throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> ctr = clazz.getConstructor(Date.class, String.class);
        return ctr.newInstance(arg1, arg2);
    }

    @Benchmark
    public Object find_constructor_allocate() throws Exception {
        Constructor<?> ctr = klass.getConstructor(Date.class, String.class);
        return ctr.newInstance(arg1, arg2);
    }


    @Benchmark
    public Object conctructor_allocate() throws Exception {
        return contructor.newInstance(arg1, arg2);
    }

    @Benchmark
    public Object new_allocate() throws Exception {
        return new C(arg1, arg2);
    }
}
