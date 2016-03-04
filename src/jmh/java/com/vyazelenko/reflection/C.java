package com.vyazelenko.reflection;

import java.util.Date;
import java.util.List;

class C extends B {
    protected String caption;
    private int x;
    private static final int CONST = 18;
    private List<B> history;
    long sum;

    public C(Date timestamp, String caption) {
        super(timestamp, caption);
    }
}
