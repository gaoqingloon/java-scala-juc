package com.lolo.javaUtral.jmm;

import java.util.concurrent.atomic.AtomicInteger;

class MyData {  // MyData.java => MyData.class => JVM字节码

    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    // 注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() {  // synchronized 可以
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1. 验证 volatile的可见性
 *      1.1 假如 int number = 0; number变量之前根本没有添加 volatile关键字修饰，没有可见性
 *      1.2 添加了volatile，可以解决可见性问题
 *
 * 2. 验证 volatile不保证原子性
 *      2.1 原子性指的是什么意思？
 *          不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，
 *          需要整体完整，要么同时成功，要么同时失败。
 *      2.2 volatile不保证原子性的案例演示
 *      2.3 why
 *      2.4 如何解决原子性？
 *          * 加sync
 *          * 使用我们的juc下AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {

        //seeOkByVolatile();

        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 10000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待20个线程都全部计算完成后，再用main线程取得最终的结果看是多少？
        // 暂停一会儿线程
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        while (Thread.activeCount() > 2) {  // main gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t" + myData.atomicInteger);
    }

    // volatile 可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();  // 资源类
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " updated data: " + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
        }

        System.out.println(Thread.currentThread().getName() + " main data: " + myData.number);
    }
}
