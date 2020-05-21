package com.lolo.javaUtral.jvm;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    // 内存够用的时候就保留，不够用就回收
    public static void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
        /*
        java.lang.Object@12a3a380
        java.lang.Object@12a3a380
        null
        java.lang.Object@12a3a380
         */
    }

    /**
     * JVM配置，故意产生大对象并配置小的内存，让它内存不够用了导致00M，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
        //softRefMemoryEnough();
        softRefMemoryNotEnough();
    }
}
