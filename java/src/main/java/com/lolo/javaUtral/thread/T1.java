package com.lolo.javaUtral.thread;

import java.util.concurrent.locks.ReentrantLock;

public class T1 {

    volatile int n = 0;
    public void add() {
        n++;
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);  // 默认false 非公平锁（是否队列）
    }
}
