package com.lolo.javaUtral.jmm;

public class T1 {

    volatile int number = 0;

    public void add() {
        number++;
    }
}
