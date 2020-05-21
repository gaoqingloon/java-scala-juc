package com.lolo.javaUtral.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS 是什么？  ==> compareAndSet
 * 比较和交换
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        // main do something ...
        System.out.println(atomicInteger.compareAndSet(5, 2019) +
                "\t current data: " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) +
                "\t current data: " + atomicInteger.get());
    }
}
