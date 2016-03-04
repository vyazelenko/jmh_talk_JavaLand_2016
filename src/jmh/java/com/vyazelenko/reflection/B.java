package com.vyazelenko.reflection;

import java.util.Date;

class B extends A {
    public String name;
    private byte[] timestamp;
    private long f1;
    private long f2;
    private long f3;
    int hashCode;

    public B(Date timestamp, String caption) {
        super(timestamp, caption);
    }
}
