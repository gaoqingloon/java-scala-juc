package com.itedu.lesson_04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile 修饰不具有原子性（不具有同步性）
 */
public class VolatileNoAtomicDemo {

    public static void main(String[] args) {

        // 初始化10个线程
        VolatileNoAtomic[] volatileNoAtomics = new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomics.length; i++) {
            // 创建每一个线程
            volatileNoAtomics[i] = new VolatileNoAtomic();
        }
        for (int i = 0; i < volatileNoAtomics.length; i++) {
            // 启动每一个线程
            volatileNoAtomics[i].start();
        }
        /*
        Thread-4---2000
        Thread-0---2000
        Thread-2---3000
        Thread-6---4000
        Thread-3---5000
        Thread-5---6000
        Thread-8---7146
        Thread-7---7843
        Thread-1---8843
        Thread-9---9843

        Thread-0---1000
        Thread-3---2000
        Thread-4---3000
        Thread-1---4000
        Thread-5---5000
        Thread-7---6000
        Thread-9---7000
        Thread-2---8000
        Thread-6---9000
        Thread-8---10000
         */
    }
}


class VolatileNoAtomic extends Thread {

    // private static 只初始化一次，相当于多个线程的全局变量
    //private static volatile int count = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            //count++;
            atomicInteger.incrementAndGet();  // count++
        }
        System.out.println(getName() + "---" + atomicInteger);
    }
}
