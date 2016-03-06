package com.vyazelenko.map;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class MapGet {
    private String key;
    private Map<String, Integer> treeMap;
    private Map<String, Integer> hashMap;
    private Map<String, Integer> ignoreCaseMap;

    @Setup
    public void setUp() {
        key = "GoodKey";
        treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        hashMap = new HashMap<>();
        ignoreCaseMap = new HashMap<>();
        String[] data = getData();
        for (String d : data) {
            treeMap.put(d, d.length());
            hashMap.put(d.toLowerCase(), d.length());
            ignoreCaseMap.put(d, d.length());
            ignoreCaseMap.put(d.toLowerCase(), d.length());
        }
    }

    private String[] getData() {
        return ("Microblogging and social networking sites that limit the number of characters in a message " +
                "(most famously Twitter, where the 140-character limit can be quite restrictive in languages that rely " +
                "on alphabets, including English) are potential outlets for medial capitals. Using CamelCase between " +
                "words reduces the number of spaces, GoodKey and thus the number of characters, in a given message, allowing " +
                "more content to fit into the limited space. Hashtags, especially long ones, often use CamelCase to " +
                "maintain readability (e.g. #CollegeStudentProblems is easier to read than #collegestudentproblems )."
                + "A collection of Concurrent and Highly Scalable Utilities. These are intended as direct replacements " +
                "for the java.util.* or java.util.concurrent.* collections but with better performance when many CPUs " +
                "are using the collection concurrently. baD_kEy")
                .split("\\s+");
    }

    @Benchmark
    public Integer HashMap_toLowerCase() {
        return hashMap.get(key.toLowerCase());
    }

    @Benchmark
    public Integer TreeMap() {
        return treeMap.get(key);
    }

    @Benchmark
    public Integer IgnoreCaseMap() {
        Integer val = ignoreCaseMap.get(key);
        if (val == null) {
            val = ignoreCaseMap.get(key.toLowerCase());
            if (val != null) {
                ignoreCaseMap.put(key, val);
            }
        }
        return val;
    }

}
